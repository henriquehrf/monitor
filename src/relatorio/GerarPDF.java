/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorio;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.PdfContentByte;

import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import controller.GraficoController;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import negocio.DadosCache;
import negocio.GerarEstatistica;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Henrique Firmino
 */
public class GerarPDF {

//    public void imprimir() {
//
//       
//        Document document = new Document();
//        try {
//            //C:\Users\Henrique Firmino\Desktop
//
//            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Henrique Firmino\\Desktop\\pdf\\PDF_DevMedia.pdf"));
//            document.open();
//
//            // adicionando um parágrafo no documento
//            document.add(new Paragraph("Gerando PDF - Java"));
//        } catch (Exception de) {
//            System.err.println(de.getMessage());
//        } finally {
//
//            document.close();
//
//        }
//    }
    //-------------------------------------------------------------------------------
//    
//    public static JFreeChart generatePieChart() {
//        DefaultPieDataset dataSet = new DefaultPieDataset();
//        dataSet.setValue("China", 19.64);
//        dataSet.setValue("India", 17.3);
//        dataSet.setValue("United States", 4.54);
//        dataSet.setValue("Indonesia", 3.4);
//        dataSet.setValue("Brazil", 2.83);
//        dataSet.setValue("Pakistan", 2.48);
//        dataSet.setValue("Bangladesh", 2.38);
//
//        JFreeChart chart = ChartFactory.createPieChart(
//                "World Population by countries", dataSet, true, true, false);
//
//        return chart;
//    }
//
//    public static JFreeChart generateBarChart() {
//        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
//        dataSet.setValue(791, "Population", "1750 AD");
//        dataSet.setValue(978, "Population", "1800 AD");
//        dataSet.setValue(1262, "Population", "1850 AD");
//        dataSet.setValue(1650, "Population", "1900 AD");
//        dataSet.setValue(2519, "Population", "1950 AD");
//        dataSet.setValue(6070, "Population", "2000 AD");
//
//        JFreeChart chart = ChartFactory.createBarChart(
//                "World Population growth", "Year", "Population in millions",
//                dataSet, PlotOrientation.VERTICAL, false, true, false);
//
//        return chart;
//    }
//
//    public static void main(String[] args) {
//        writeChartToPDF(generateBarChart(), 500, 400, "C:\\Users\\Henrique Firmino\\Desktop\\pdf\\barchart.pdf");
//        writeChartToPDF(generatePieChart(), 500, 400, "C:\\Users\\Henrique Firmino\\Desktop\\pdf\\piechart.pdf");
//    }
//
//    public static void writeChartToPDF(JFreeChart chart, int width, int height, String fileName) {
//        PdfWriter writer = null;
//
//        Document document = new Document();
//
//        try {
//            writer = PdfWriter.getInstance(document, new FileOutputStream(
//                    fileName));
//            document.open();
//            PdfContentByte contentByte = writer.getDirectContent();
//            PdfTemplate template = contentByte.createTemplate(width, height);
//            Graphics2D graphics2d = template.createGraphics(width, height,
//                    new DefaultFontMapper());
//            Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
//                    height);
//
//            chart.draw(graphics2d, rectangle2d);
//
//            graphics2d.dispose();
//            contentByte.addTemplate(template, 0, 0);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        document.close();
//    }
    //---------------------------------------------------------------------------------------
    public static XYDataset createDataset() {

        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries series1 = new XYSeries("Object 1");
        XYSeries series2 = new XYSeries("Object 2");
        XYSeries series3 = new XYSeries("Object 3");

        series1.add(1.0, 2.3);
        series1.add(2.0, 3.0);
        series1.add(3.0, 2.5);
        series1.add(3.5, 2.8);
        series1.add(4.2, 6.0);

        series2.add(2.0, 1.0);
        series2.add(2.5, 2.4);
        series2.add(3.2, 1.2);
        series2.add(3.9, 2.8);
        series2.add(4.6, 3.0);

        series3.add(1.2, 4.0);
        series3.add(2.5, 4.4);
        series3.add(3.8, 4.2);
        series3.add(4.3, 3.8);
        series3.add(4.5, 4.0);

        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        //  JFreeChart chart = ChartFactory.createLineChart(title, categoryAxisLabel, valueAxisLabel, dataset)
        return dataset;
    }

    public static JFreeChart createChart(XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Line Chart Demo 6", // chart title
                "X", // x axis label
                "Y", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
        );
        return chart;
    }

    public List<JFreeChart> add(List<DadosCache> dado, String header, boolean enableTSolo, boolean enableTAmbiente, boolean enableUmidade) {
        List<JFreeChart> printer = new ArrayList<>();
        GerarEstatistica estat = new GerarEstatistica(0, 0, GraficoController.memoria);
        estat.calcular();
        System.out.println(estat.getT_Solo_Ambiente());
        String cabecalho = header + "\n";
        if (enableTAmbiente) {
            cabecalho += estat.getTemp_Ambiente();
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            for (int i = 0; i < dado.size(); i++) {
                ds.addValue(dado.get(i).getTemp_dth(), "Temperatura Ambiente", dado.get(i).getData());
            }
            JFreeChart grafico = ChartFactory.createLineChart(cabecalho, "Periodo", "Variação da Temperatura", ds, PlotOrientation.VERTICAL, true, true, false);
            printer.add(grafico);
        }
        if (enableTSolo) {
            cabecalho = header + "\n";
            cabecalho += estat.getTemp_Solo();
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            for (int i = 0; i < dado.size(); i++) {
                ds.addValue(dado.get(i).getTemp_ds(), "Temperatura do Solo", dado.get(i).getData());
            }
            JFreeChart grafico = ChartFactory.createLineChart(cabecalho, "Periodo", "Variação da Temperatura", ds, PlotOrientation.VERTICAL, true, true, false);
            printer.add(grafico);
        }
        if (enableUmidade) {
            cabecalho = header + "\n";
            cabecalho += estat.getUmidade();
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            for (int i = 0; i < dado.size(); i++) {
                ds.addValue(dado.get(i).getUmidade_dht(), "Umidade", dado.get(i).getData());
            }
            JFreeChart grafico = ChartFactory.createLineChart(cabecalho, "Periodo", "Variação da Umidade", ds, PlotOrientation.VERTICAL, true, true, false);
            printer.add(grafico);
        }
        if (enableTAmbiente && enableTSolo) {
            cabecalho = header + "\n";
            cabecalho+=estat.getT_Solo_Ambiente();
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            //   DefaultCategoryDataset ds2 = new DefaultCategoryDataset();

            for (int i = 0; i < dado.size(); i++) {
                ds.addValue(dado.get(i).getTemp_ds(), "Temperatura do Solo ", dado.get(i).getData());
                ds.addValue(dado.get(i).getTemp_dth(), "Temperatura Ambiente ", dado.get(i).getData());
            }
            JFreeChart grafico = ChartFactory.createLineChart(cabecalho, "Periodo", "Variação da Temperatura", ds, PlotOrientation.VERTICAL, true, true, false);

            //    grafico = ChartFactory.createXYLineChart(header, header, header, dataset)
            printer.add(grafico);
        }

//        DefaultCategoryDataset ds = new DefaultCategoryDataset();
//        ds.addValue(70.20, "maximo", "07-02-2017 06:51:33");
//        JFreeChart grafico = ChartFactory.createLineChart("Meu Grafico", "Dia",
//                "Valor", ds, PlotOrientation.VERTICAL, true, true, false);
        return printer;
    }

//    public static void main(String[] args) {
//        // XYDataset aux = createDataset();
//        writeChartToPDF(add(), 800, 600, "C:\\Users\\Henrique Firmino\\Desktop\\pdf\\henriquePDF2.pdf");
//        //  writeChartToPDF(generatePieChart(), 500, 400, "C:\\Users\\Henrique Firmino\\Desktop\\pdf\\piechart.pdf");
//    }
    public void writeChartToPDF(List<JFreeChart> chart, int width, int height, String fileName) {
        PdfWriter writer = null;

        Document document = new Document();
        try {

            document.setPageSize(PageSize.A4.rotate());
            // document.add(new Paragraph("Gerando PDF - Java"));

            writer = PdfWriter.getInstance(document, new FileOutputStream(
                    fileName));

            document.open();
            PdfContentByte contentByte = writer.getDirectContent();

            for (int i = 0; i < chart.size(); i++) {

                document.newPage();

                PdfTemplate template = contentByte.createTemplate(width, height);

                Graphics2D graphics2d = template.createGraphics(width, height,
                        new DefaultFontMapper());
                Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
                        height);

                chart.get(i).draw(graphics2d, rectangle2d);

                graphics2d.dispose();
                contentByte.addTemplate(template, 0, 0);

                //document.add(new Paragraph("Novo parágrafo na nova página"));
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
