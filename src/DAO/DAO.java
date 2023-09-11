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

package DAO;

/**
 * Febrero del 2014
 *
 * @author Máximo Coejo Cores mcoejo@gmail.com
 */
import DataTable.JDataTable;
import Parametros.conf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {


    Connection con = null;

    public DAO() throws ClassNotFoundException {
            Class.forName(conf.className);
  }

    /**
     * Constructor expecifico, se le pasa el driver por parametro
     *
     * @param SQLClientDriver
     * @throws ClassNotFoundException
     */
    public DAO(String SQLClientDriver) throws ClassNotFoundException {
        Class.forName(SQLClientDriver);
    }

    /**
     * Activa la conexion a la BD si no esta conectada
     *
     * @throws SQLException
     */
    private void conectar() throws SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(conf.strconex  + conf.ip + conf.BD, conf.user, conf.passw);
      }
    }
    
    
    public boolean serverOnline(){
        try{
             this.conectar();
             this.con.close();
             return true;
        }catch(SQLException ex){
            return false;
        }
        
        
    }


    /**
     * Ejecuta la String SQL en el servidor BD
     *
     * @param SQL
     * @return numero de filas modificadas
     * @throws SQLException
     */
    public int insertar(String SQL) throws SQLException {
        int registro = 0;
        System.out.println(SQL);
     
        this.conectar();
        Statement stm = con.createStatement();

        registro = stm.executeUpdate(SQL);

        if (registro > 0) {
            System.out.println("ok" + registro);
        }
        con.close();
        return registro;
    }

   
    /**
     * Retorna la tabla de la Consulta SELECT del SQL
     *
     * @param selectSQL
     * @return JDataTable
     * @throws SQLException
     * @throws Exception
     */
    public JDataTable getSelect(String selectSQL) throws SQLException, Exception {
        ResultSet rs;
        this.conectar();
        System.out.println(selectSQL );
        Statement stm = con.createStatement();
        rs = stm.executeQuery(selectSQL);
        JDataTable ta = new JDataTable(rs);
        if (!con.isClosed()) {
            con.close();
        }
        return ta;
    }

    

    

}