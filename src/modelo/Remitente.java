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
package modelo;

/**
 * Febrero del 2014
 * Clase para guardar el contenido de los email 
 * @author Máximo Coejo Cores mcoejo@gmail.com
 */
public class Remitente {

    String Email;
    String texto;
    String body;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Remitente(String Email, String texto) {
        this.Email = Email;
        this.texto = texto;
    }

    public Remitente(String Email, String texto, String body) {
        this.Email = Email;
        this.texto = texto;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Remitente() {
        this.Email = "";
        this.texto = "";
        this.body = "";
    }

    @Override
    public String toString() {
        return "Email=" + Email + " texto=" + texto + " " + this.body;
    }

}
