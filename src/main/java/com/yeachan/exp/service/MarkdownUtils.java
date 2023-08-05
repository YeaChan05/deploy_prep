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
        // 정규표현식 패턴
        String dataPattern = "(data:(image|audio|video|application|font|text)/[^;]+;base64,[^\\s]+)";
        String linksPattern = "\\b(?:https?://)[^\\s]+";
        String headersPattern = "(#{1,6}\\s*)"; // 1~6 개의 # 뒤에 공백이 있는 경우 (H1 ~ H6)
        String imagesPattern = "\\b(\\w+\\.(?i)(jpg|jpeg|png|gif|bmp|webp|svg|tiff))\\b";
        
        text = text.replaceAll(imagesPattern, "");
        
        text = text.replaceAll(dataPattern, "");
        
        text = text.replaceAll(linksPattern, "");
        
        text = text.replaceAll("\\[([^\\]]*)\\]\\([^\\)]*\\)", "");
        
        text = text.replaceAll("<img[^>]*>", "");

        text = text.replaceAll(headersPattern, "");

        text = text.replaceAll("[*_|`~<>]", "");
        
        text = text.replaceAll("\"", "");
        
        text = text.replace("(", "");
        
        return text;
    }


}
