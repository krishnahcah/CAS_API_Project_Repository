package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

// Create the Ade skipped TCs Report
public class SkippedTCsReportGen {

    private StringBuilder HTMLContent = new StringBuilder(setHTMLHeader());

    public void generateSkippedTCsReport(HashMap<String, String> skippedTCs) {

        boolean generateReportFlag = false;

        Iterator<String> iterator = skippedTCs.keySet().iterator();

        while (iterator.hasNext()) {

            String key = iterator.next();
            String value = skippedTCs.get(key);

            HTMLContent.append(getRowHtmlString(key, value));
            generateReportFlag = true;
        }
        if (generateReportFlag) {
            AlertUtils.simplemail("Test mail", "Regression skipped Test cases Report", "ravizigzak@gmail.com");
        }
    }

    private String setHTMLHeader() {

        String header = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<title>TestNG:  Unit Test</title>\n" +
                "<style type=\"text/css\">\n" +
                ".tg  {border-collapse:collapse;border-spacing:0;}\n" +
                ".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}\n" +
                ".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}\n" +
                ".tg .tg-3oug{background-color:#fd6864;text-align:left;vertical-align:top}\n" +
                ".tg .tg-c3ow{border-color:inherit;text-align:left;vertical-align:top}\n" +
                ".tg .tg-m3fy{font-weight:bold;font-size:14px;background-color:#efefef;text-align:left;vertical-align:top}\n" +
                ".tg .tg-7tmp{font-weight:bold;font-style:italic;font-size:14px;background-color:#efefef;text-align:left;vertical-align:top}\n" +
                ".tg .tg-jwog{font-weight:bold;font-style:italic;font-size:14px;background-color:#dae8fc;border-color:inherit;text-align:left;vertical-align:top}\n" +
                ".tg .tg-1hbz{font-style:italic;font-size:14px;background-color:#dae8fc;border-color:inherit;text-align:left;vertical-align:top}\n" +
                ".tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}\n" +
                ".tg .tg-0qe0{background-color:#ecf4ff;text-align:left;vertical-align:top}\n" +
                ".tg .tg-og4q{background-color:#fd6864;text-align:left;vertical-align:top}\n" +
                ".tg .tg-0lax{text-align:left;vertical-align:top}\n" +
                "</style>\n" +
                "<table class=\"tg\">\n" +
                "  <tr>\n" +
                "    <th class=\"tg-jwog\"><span style=\"font-weight:700\">TestCase Name</span></th>\n" +
                "    <th class=\"tg-jwog\"><span style=\"font-weight:700\">Skipped Reason</span></th>\n" +
                "  </tr>";

        return header;
    }

    private String getRowHtmlString(String testCaseName, String comment) {
        return "<tr>\n" +
                "    <td class=\"tg-c3ow\">" + testCaseName + "</td>\n" +
                "    <td class=\"tg-c3ow\">" + comment + "</td>\n" +
                "  </tr>";
    }

    private String readConfigData(String property) {
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream("src/main/java/customReportGenerator/resources/customReport.properties");
            prop.load(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty(property);
    }
}
