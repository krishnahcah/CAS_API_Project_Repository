package utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class AdeHttpClient {


    public static void main(String[] args) {

        AdeHttpClient adeHttpClient = new AdeHttpClient();
        String testUrl = "https://originqaade.clmbtech.com/cde/data/v5.htm?id=129118~1~0&_v=0&_u=http://toidev.indiatimes.com/city/hyderabad/following-ikea-rush-cops-issue-traffic-curbs/articleshow/65346542.cms";

        ArrayList<String> reponse_callident = adeHttpClient.getResponses(testUrl);

        System.out.println(" response = " + reponse_callident.get(0));
        System.out.println(" X-IDENTIFIER  =  " + reponse_callident.get(1));

    }

    public ArrayList<String> getResponses(String url) {

        ArrayList<String> arrayList = new ArrayList<>();
        int responseCode = 0;
        CloseableHttpClient client = null;
        int DEFAULT_TIMEOUT = 100000;
        try {
            // System.setProperty("jsse.enableSNIExtension", "false");
            String baseUrl = url.substring(0, url.indexOf('?'));
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(DEFAULT_TIMEOUT)
                    .setConnectionRequestTimeout(DEFAULT_TIMEOUT)
                    .setSocketTimeout(DEFAULT_TIMEOUT)
                    .build();
            SocketConfig socketConfig = SocketConfig.custom()
                    .setTcpNoDelay(true)
                    .build();
            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
            connManager.setDefaultSocketConfig(socketConfig);
            HttpClientBuilder builder = HttpClients.custom();
            builder.setConnectionManager(connManager);
            builder.setDefaultRequestConfig(requestConfig);
//            builder.setDefaultHeaders(new ArrayList<>());
            builder.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
            builder.setSSLContext(new SSLContextBuilder().loadTrustMaterial(TrustSelfSignedStrategy.INSTANCE).build());
            client = builder.build();
            List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), Charset.forName("UTF-8"));
            String paramString = URLEncodedUtils.format(params, Charset.forName("UTF-8"));
            url = baseUrl + "?" + paramString;
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse response = client.execute(httpGet);

            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                String response1 = EntityUtils.toString(responseEntity);
                arrayList.add(response1);
            }

            for (Header header : response.getAllHeaders()) {
                if (header.getName().equalsIgnoreCase("X-IDENTIFIER")) {
                    arrayList.add(header.getValue());
                    break;
                }
            }

            responseCode = response.getStatusLine().getStatusCode();
            response.close();

        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
            responseCode = 200;
        } catch (SocketException e) {
            System.out.println(e.getMessage());
            responseCode = 200;
        } catch (ClientProtocolException e) {
            System.out.println(e.getMessage());
            responseCode = 200;
        } catch (SSLException e) {
            System.out.println(e.getMessage());
            responseCode = 200;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return arrayList;
    }
}

