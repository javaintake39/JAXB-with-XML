package jaxlab2;

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
            Content content = (Content) JAXBChatMessage.getValue();
            System.out.println(content.getMessages());
            List<MessageInfo> messageInfos = (List<MessageInfo>) content.getMessages().getMessage();
            for (int i = 0; i < messageInfos.size(); i++) {
                System.out.println("To        >" + messageInfos.get(i).getToPhone());
                System.out.println("From      >" + messageInfos.get(i).getFromPhone());
                System.out.println("Content   >" + messageInfos.get(i).getContent());
                System.out.println("Date      >" + messageInfos.get(i).getDate());
                System.out.println("fontColor >" + messageInfos.get(i).getFontColor());
                System.out.println("fontSize  >" + messageInfos.get(i).getFontSize());
                System.out.println("fontFamily >" + messageInfos.get(i).getFontFamily());
                System.out.println("----------------------------------------");
            }

            ObjectFactory factory = new ObjectFactory();
            JAXBElement History = factory.createHistory(content);
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
