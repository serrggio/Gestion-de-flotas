/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author sergi
 */
public class GestorReportesXML {

    // 1. Generación DOM: Crea el archivo estructurado en árbol
    public void generarInformeDOM(File fichero, ArrayList<Vehiculo> vehiculos) {
        fichero.getParentFile().mkdirs();
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("Flota");
            doc.appendChild(root);

            for (Vehiculo v : vehiculos) {
                Element vehiculo = doc.createElement("Vehiculo");

                Element matricula = doc.createElement("Matricula");
                matricula.appendChild(doc.createTextNode(v.getMatricula()));
                vehiculo.appendChild(matricula);

                Element categoria = doc.createElement("Categoria");
                categoria.appendChild(doc.createTextNode(v.getCategoria()));
                vehiculo.appendChild(categoria);
                
                root.appendChild(vehiculo);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fichero);
            transformer.transform(source, result);
            
            System.out.println("Auditoría XML generada con éxito.");

        } catch (Exception e) {
            System.out.println("Error al generar XML DOM: " + e.getMessage());
        }
    }

    // 2. Lectura SAX: Procesa el XML y extrae categorías únicas
    public HashSet<String> procesarInformeSAX(File fichero) {
        HashSet<String> categoriasUnicas = new HashSet<>();
        
        if (!fichero.exists()) return categoriasUnicas;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean esCategoria = false;

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("Categoria")) {
                        esCategoria = true;
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (esCategoria) {
                        // El HashSet garantiza automáticamente que no haya repetidos
                        categoriasUnicas.add(new String(ch, start, length).trim());
                        esCategoria = false;
                    }
                }
            };

            saxParser.parse(fichero, handler);
            System.out.println("Categorías de mantenimiento registradas: " + categoriasUnicas);

        } catch (Exception e) {
            System.out.println("Error al leer XML SAX: " + e.getMessage());
        }
        
        return categoriasUnicas;
    }
}