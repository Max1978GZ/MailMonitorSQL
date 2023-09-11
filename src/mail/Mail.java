 /*
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package mail;

/**
 * Febrero del 2014
 *
 * @author Máximo Coejo Cores mcoejo@gmail.com
 * Adaptacion del codigo de email de http://www.chuidiang.com/java/herramientas/javamail/empezar-javamail.php
 */
import java.util.Properties;
import java.util.Random;
import javax.mail.Folder;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Ejemplo de envio de correo simple con JavaMail
 *
 *
 *
 */
public class Mail {

    /*
    
     */
    public static void EnviarMail(String cadena, String formail) {
        try {

            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "mailforsql@gmail.com");
            props.setProperty("mail.smtp.auth", "true");
            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(formail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(formail));
            Random a = new Random();
            message.setSubject("DATOS DE RETORNO");
            message.setText(cadena);
            Transport t = session.getTransport("smtp");
            t.connect(Parametros.conf.email, Parametros.conf.passwEmail);
            t.sendMessage(message, message.getAllRecipients());

            t.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static modelo.Remitente RecibirMail(String[] args) {

        modelo.Remitente re = new modelo.Remitente();

        Properties prop = new Properties();
        prop.setProperty("mail.pop3.starttls.enable", "false");
        prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.pop3.socketFactory.fallback", "false");
        prop.setProperty("mail.pop3.port", "995");
        prop.setProperty("mail.pop3.socketFactory.port", "995");
        Session sesion = Session.getInstance(prop);

        try {

            Store store = sesion.getStore("pop3");
            store.connect("pop.gmail.com", Parametros.conf.email, Parametros.conf.passwEmail);
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            Message[] mensajes = folder.getMessages();
            if (0 == mensajes.length) {
                System.out.println("No hay mensajes");
                return re;
            }

            re = new modelo.Remitente(mensajes[mensajes.length - 1].getFrom()[0].toString(), mensajes[mensajes.length - 1].getSubject(), mensajes[mensajes.length - 1].getContent().toString());

            folder.close(false);
            store.close();
        } catch (com.sun.mail.util.MailConnectException ex) {
            System.out.println("Error de conexion con el servidor de mail\n" + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error indeterminado " + e.getMessage());
        }
        return re;

    }

}
