package utils;

import java.util.List;

public class MailProp {

    String from;
    String fromPassWord;
    String to;
    String cc;
    String subject;
    String mailTextForHtmlTable;
    String content;
    String htmlTable;
    List<String> attachmentName;
    String attachmentPattern;


    public String getMailTextForHtmlTable() {
        return mailTextForHtmlTable;
    }

    public void setMailTextForHtmlTable(String mailTextForHtmlTable) {
        this.mailTextForHtmlTable = mailTextForHtmlTable;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromPassWord() {
        return fromPassWord;
    }

    public void setFromPassWord(String fromPassWord) {
        this.fromPassWord = fromPassWord;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(List<String> attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getHtmlTable() {
        return htmlTable;
    }

    public void setHtmlTable(String htmlTable) {
        this.htmlTable = htmlTable;
    }

    public String getAttachmentPattern() {
        return attachmentPattern;
    }

    public void setAttachmentPattern(String attachmentPattern) {
        this.attachmentPattern = attachmentPattern;
    }
}
