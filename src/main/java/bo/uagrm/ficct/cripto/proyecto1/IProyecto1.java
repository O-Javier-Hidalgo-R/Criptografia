/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bo.uagrm.ficct.cripto.proyecto1;

import java.util.ArrayList;

/**
 *
 * @author OJavierHR
 */
public interface IProyecto1 {
    void EliminarAlfabeto();
    boolean esVacio();
    /*  No es necesario hacer por desplazamiento puro sin y con clave basta 
     *  con el que tiene clave.
     *  String encriptadoDesplazamientoPuro(String mensaje, int desplazamiento);
     */
    public ArrayList<Character> getAlfabeto();
    String encriptadoTransposicionGrupos(String mensaje, String clave); //preguntar
    String desencriptadoTransposicionGrupos(String mensaje, String clave);
    String encriptadoTransposicionSeries(String mensaje); //preguntar
    String desencriptadoTransposicionSeries(String mensaje);
    String encriptadoTransposicionColumnas(String mensaje, String clave);
    String desencriptadoTransposicionColumnas(String mensaje, String clave);
    String encriptadoTransposicionFilas(String mensaje, String clave);
    String desencriptadoTransposicionFilas(String mensaje, String clave);
    String encriptadoTransposicionZigZag(String mensaje, int filas);
    String desencriptadoTransposicionZigZag(String mensaje, int filas);
    String encriptadoSustitucionDesplazamientoGenerico(String mensaje, 
            int desplazamiento, int decimacion, String clave);
    String desencriptadoSustitucionDesplazamientoGenerico(String mensaje, 
            int desplazamiento, int decimacion, String clave);
    String encriptadoSustitucionPoliAlfabetica(String mensaje);
    String desencriptadoSustitucionPoliAlfabetica(String mensaje);
    String encriptadoSustitucionHomofonosPrimerOrden(String mensaje, 
            int[][] tabla);
    String desencriptadoSustitucionHomofonosPrimerOrden(String mensaje, 
            int[][] tabla);
    String encriptadoSustitucionHomofonosOrdenMayor(int[][] tabla, 
            String mensajeCorrecto, String mensajeFalso, 
            boolean enFuncionFilas);
    String desencriptadoSustitucionHomofonosOrdenMayor(int[][] tabla, 
            String mensajeEncriptado, boolean enFuncionFilas) ;
    String encriptadoSustitucionPolialfabeticosPeriodicos(String mensaje,
            String clave);
    String desencriptadoSustitucionPolialfabeticosPeriodicos(String mensaje,
            String clave);
}
