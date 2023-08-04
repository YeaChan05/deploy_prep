package com.yeachan.exp.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;
import org.springframework.web.util.UriUtils;

public class MarkdownUtils {
    
    public static String convertToEncodedMarkdown(byte[] content) {
        String markdown = new String(content); // Assuming content is UTF-8 encoded
        return UriUtils.encodePath(markdown, "UTF-8");
    }
    public static String convertToTextContent(byte[] content) {
        String markdown = new String(content); // Assuming content is UTF-8 encoded
        Node document = parseMarkdown(markdown);
        String textContent = renderTextContent(document);
        return removeDataUrlsAndLinks(textContent);
    }
    
    private static Node parseMarkdown(String markdown) {
        Parser parser = Parser.builder().build();
        return parser.parse(markdown);
    }
    
    private static String renderTextContent(Node document) {
        TextContentRenderer textContentRenderer = TextContentRenderer.builder().build();
        return textContentRenderer.render(document);
    }
    
    private static String removeDataUrlsAndLinks(String text) {
        String pattern = "(data:(image|audio|video|application|font|text)/[^;]+;base64,[^\\s]+)|\\b(?:https?://)[^\\s]+";
        text = text.replaceAll(pattern, "");
        text = text.replaceAll("\"", "");
        text = text.replace("(", "");
        return text;
    }

}
