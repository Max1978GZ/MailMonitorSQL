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
package Parametros;

import java.io.IOException;
import java.util.Properties;

/**
 * Febrero del 2014
 *
 * @author Máximo Coejo Cores mcoejo@gmail.com
 */
public class conf {

    public static String ip;
    public static String className;
    public static String BD;
    public static String user;
    public static String passw;
    public static String strconex;
    public static String email;
    public static String passwEmail;

    public static enum TypeBd {

        MySql, DerbyDb, SqlServer, MariaDb
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        conf.ip = ip;
    }

    public static String getClassName() {
        return className;
    }

    public static void setClassName(String className) {
        conf.className = className;
    }

    public static String getBD() {
        return BD;
    }

    public static void setBD(String BD) {
        conf.BD = BD;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        conf.user = user;
    }

    public static String getPassw() {
        return passw;
    }

    public static void setPassw(String passw) {
        conf.passw = passw;
    }

    public static String getStrconex() {
        return strconex;
    }

    public static void setStrconex(String strconex) {
        conf.strconex = strconex;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        conf.email = email;
    }

    public static String getPasswEmail() {
        return passwEmail;
    }

    public static void setPasswEmail(String passwEmail) {
        conf.passwEmail = passwEmail;
    }

    @Override
    public String toString() {
        return "conf{" + '}';
    }

    /**
     * carga la configuracion del archivo de configuraciones
     *
     *
     * @param TypeDb
     */
    public void getConf(String TypeDb) {
        try {

            Properties propiedades = new Properties();

            switch (TypeDb) {
                case "MySql":
                    /**
                     * para configurar conexion a mysql en proceso
                     */
                    propiedades.load(this.getClass().getResourceAsStream("mysql"));
                    break;
                case "DerbyDb":
                default:
                    propiedades.load(this.getClass().getResourceAsStream("derby"));
                    break;

            }

            if (!propiedades.isEmpty()) {
                conf.ip = propiedades.getProperty("Ip");
                conf.className = propiedades.getProperty("ClassName");
                conf.BD = propiedades.getProperty("BD");
                conf.user = propiedades.getProperty("Usuario");
                conf.passw = propiedades.getProperty("Pass");
                conf.strconex = propiedades.getProperty("Conexion");
                conf.email = propiedades.getProperty("Email");
                conf.passwEmail = propiedades.getProperty("Passw");

            } else {
                System.out.println("Error al intentar obtener la configuración");
            }
        } catch (IOException ex) {
            System.out.println("Error al intentar leer el archivo de configuración");

        }
    }

}
