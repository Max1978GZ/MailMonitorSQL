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
package mailmonitorsql;

/**
 * Febrero del 2014
 *
 * @author Máximo Coejo Cores mcoejo@gmail.com
 */
public class MailMonitorSQL {

    public static String strIniHtml = "<html><head> <meta charset=\"UTF-8\">\n <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"></head><body>\n";
    public static String strFinHtml = "\n</body></html>";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        do {

            Parametros.conf p = new Parametros.conf();
            p.getConf("DerbyDb");
            String cadena = "", a = "";
            DAO.DAO dao = new DAO.DAO();
            try {
                System.out.println();
                System.out.println("Conectando al correo");
                modelo.Remitente re = mail.Mail.RecibirMail(args);
                System.out.println("FROM =" + re.getEmail());
                System.out.println("SUBJET =" + re.getTexto());

                a = re.getTexto();
                if (a.length() > 4 && a.contains("=") && a.contains("[") && a.contains("]")) {

                    if (a.contains("DNI=")) {
                        String dni = a.substring(a.indexOf("[") + 1, a.indexOf("]"));
                        System.out.println("Conectando a la base de datos");
                        cadena = dao.getSelect("SELECT * FROM CLIENTES WHERE UPPER(DNI) LIKE UPPER('%" + dni + "%')").getTablaHtml();
                        System.out.println("Enviando " + cadena.length() + " de caracteres por email");
                        enviarMail(cadena, re.getEmail());

                    }

                    if (a.contains("NOMBRE=")) {
                        String cli = a.substring(a.indexOf("[") + 1, a.indexOf("]"));
                        System.out.println("Conectando a la base de datos");
                        cadena = dao.getSelect("SELECT * FROM CLIENTES WHERE UPPER(CLIENTE) LIKE UPPER('%" + cli + "%')").getTablaHtml();
                        cadena = strIniHtml + cadena + strFinHtml;
                        System.out.println("Enviando " + cadena.length() + " de caracteres por email");
                        enviarMail(cadena, re.getEmail());
                    }

                    if (a.contains("PROCEDIMIENTO=")) {

                        String pro = a.substring(a.indexOf("[") + 1, a.indexOf("]"));
                        System.out.println(a);
                        System.out.println("Conectando a la base de datos");
                        cadena = dao.getSelect("SELECT * FROM PROCEDIMIENTOS WHERE UPPER(PROCEDIMIENTO) LIKE UPPER('%" + pro + "%')").getTablaHtml();
                        cadena = strIniHtml + cadena + strFinHtml;
                        System.out.println("Enviando " + cadena.length() + " de caracteres por email");
                        enviarMail(cadena, re.getEmail());
                    }

                    if (a.contains("SQL=")) {

                        String pro = a.substring(a.indexOf("[") + 1, a.indexOf("]"));
                        System.out.println(a);
                        System.out.println("Conectando a la base de datos");
                        cadena = dao.getSelect(re.getBody()).getTablaHtml();
                        cadena = strIniHtml + cadena + strFinHtml;
                        System.out.println("Enviando " + cadena.length() + " de caracteres por email");
                        enviarMail(cadena, re.getEmail());
                    }

                    if (a.contains("INFORMACION")) {
                        String cad = "SINTAXIS :\nDNI=[Dni]\nNOMBRE=[pepe lopez]\nPROCEDIMIENTO=[2332/ass7223]";
                        enviarMail(cadena, re.getEmail());
                    }
                }
            } catch (Exception ax) {
                System.out.println("Error de conexion\n" + ax.getMessage());
            } finally {
                System.out.print("Esperando");
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000);
                    System.out.print(".");
                }

            }
        } while (true);

    }

    public static void enviarMail(String cadena, String email) {
        cadena = strIniHtml + cadena + strFinHtml;
        mail.Mail.EnviarMail(cadena, email);
        mail.Mail.EnviarMail(" ", "mailforsql@gmail.com");
    }

}
