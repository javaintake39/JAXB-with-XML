package jaxlab2;

import demo.Container;
import demo.Content;
import demo.MessageInfo;
import demo.ObjectFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXLab2 {

    public static void main(String[] args) {
        try {

            String xmlPath = "C:\\Users\\Abdelrhman\\Documents\\NetBeansProjects\\JAXLab2\\src\\jaxlab2\\history.xml";
            JAXBContext context = JAXBContext.newInstance("demo"); // My package
            Unmarshaller unmarsh = context.createUnmarshaller();   // convert xml content into java object

            JAXBElement JAXBChatMessage = (JAXBElement) unmarsh.unmarshal(new File(xmlPath)); // demo.mxl
            Container container = (Container) JAXBChatMessage.getValue();
            System.out.println(container.getMessages());

            List<MessageInfo> message = container.getMessages().getMessage();

            for (int i = 0; i < message.size(); i++) {
                System.out.println("To        >" + message.get(i).getToPhone());
                System.out.println("From      >" + message.get(i).getFromPhone());
                System.out.println("Content   >" + message.get(i).getContent());
                System.out.println("Date      >" + message.get(i).getDate());
                System.out.println("fontColor >" + message.get(i).getFontColor());
                System.out.println("fontSize  >" + message.get(i).getFontSize());
                System.out.println("fontFamily >" + message.get(i).getFontFamily());
                System.out.println("backgroundColor >" + message.get(i).getBackgroundColor());
                System.out.println("isBold " + message.get(i).getIsBold());
                System.out.println("isItalic " + message.get(i).getIsItalic());
                System.out.println("isUnderline " + message.get(i).getIsUnderline());
                System.out.println("----------------------------------------");
            }

            ObjectFactory factory = new ObjectFactory();
            JAXBElement History = factory.createHistory(container);
            Marshaller marsh = context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marsh.marshal(History, new FileOutputStream("output.xml"));   // After 
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JAXLab2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
