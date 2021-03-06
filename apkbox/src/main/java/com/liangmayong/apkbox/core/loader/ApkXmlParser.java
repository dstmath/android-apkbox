/**
 The MIT License (MIT)

 Copyright (c) 2017 LiangMaYong ( ibeam@qq.com )

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/ or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 **/
package com.liangmayong.apkbox.core.loader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * AppXmlParser
 *
 * @author LiangMaYong
 * @version 1.0
 */
public class ApkXmlParser extends DefaultHandler {

    Map<String, String> map = null;
    List<Map<String, String>> list = null;
    String currentTag = null;
    String currentValue = null;
    String nodeName = null;
    boolean isLoading = false;
    List<String> strings = null;

    /**
     * readXml
     *
     * @param inStream inStream
     * @param nodeName nodeName
     * @return maps
     * @throws Exception e
     */
    public static List<Map<String, String>> readXml(InputStream inStream, String nodeName) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        ApkXmlParser handler = new ApkXmlParser(nodeName);
        saxParser.parse(inStream, handler);
        inStream.close();
        return handler.getList();
    }

    /**
     * readXml
     *
     * @param inStream inStream
     * @param nodeName nodeName
     * @param form     form
     * @return maps
     * @throws Exception e
     */
    public static List<Map<String, String>> readXml(InputStream inStream, String nodeName, String[] form)
            throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        ApkXmlParser handler = new ApkXmlParser(nodeName, form);
        saxParser.parse(inStream, handler);
        inStream.close();
        return handler.getList();
    }

    private ApkXmlParser(String nodeName, String[] string) {
        this.nodeName = nodeName;
        if (string != null) {
            strings = new ArrayList<String>();
            for (int i = 0; i < string.length; i++) {
                strings.add(string[i]);
            }
        }
    }

    private ApkXmlParser(String nodeName) {
        this.nodeName = nodeName;
    }

    public List<Map<String, String>> getList() {
        return list;
    }

    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void startDocument() throws SAXException {
        list = new ArrayList<Map<String, String>>();
        isLoading = true;
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals(nodeName)) {
            map = new HashMap<String, String>();
        }
        if (attributes != null && map != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                map.put(attributes.getQName(i), attributes.getValue(i));
            }
        }
        currentTag = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentTag != null && map != null) {
            currentValue = new String(ch, start, length);
            if (currentValue != null && !currentValue.trim().equals("") && !currentValue.trim().equals("\n")) {
                if (strings != null) {
                    if (strings.contains(currentTag)) {
                        map.put(currentTag, currentValue);
                    }
                } else {
                    map.put(currentTag, currentValue);
                }
            }
        }
        currentTag = null;
        currentValue = null;
    }

    @Override
    public void endDocument() throws SAXException {
        isLoading = false;
        super.endDocument();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(nodeName)) {
            list.add(map);
            map = null;
        }
    }

}
