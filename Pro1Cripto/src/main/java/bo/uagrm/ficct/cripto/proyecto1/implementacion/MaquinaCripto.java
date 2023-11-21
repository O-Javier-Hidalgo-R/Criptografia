/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.cripto.proyecto1.implementacion;

import bo.uagrm.ficct.cripto.proyecto1.IProyecto1;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author OJavierHR
 */
public class MaquinaCripto implements IProyecto1 {

    public static final char[] ALFABETO_ESPAÑOL = {'a', 'b', 'c', 'd', 'e',
        'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static final char[] ALFABETO_INGLES = {'a', 'b', 'c', 'd', 'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
        'u', 'v', 'w', 'x', 'y', 'z'};

    public static final char[] ALFABETO_JAPONES = {'ぐ', 'だ', 'ば', 'む', 'ゐ',
        'ぁ', 'け', 'ち', 'ぱ', 'め', 'ゑ', 'あ', 'げ', 'ぢ', 'ひ', 'も', 'を',
        'ぃ', 'こ', 'っ', 'び', 'ゃ', 'ん', 'い', 'ご', 'つ', 'ぴ', 'や', 'ゔ',
        'ぅ', 'さ', 'づ', 'ふ', 'ゅ', 'ゕ', 'う', 'ざ', 'て', 'ぶ', 'ゆ', 'ゖ',
        'ぇ', 'し', 'で', 'ぷ', 'ょ', 'え', 'じ', 'と', 'へ', 'よ', 'ぉ', 'す',
        'ど', 'べ', 'ら', '゙', 'お', 'ず', 'な', 'ぺ', 'り', '゚', 'か', 'せ',
        'に', 'ほ', 'る', '゛', 'が', 'ぜ', 'ぬ', 'ぼ', 'れ', '゜', 'き', 'そ',
        'ね', 'ぽ', 'ろ', 'ゝ', 'ぎ', 'ぞ', 'の', 'ま', 'ゎ', 'ゞ', 'く', 'た',
        'は', 'み', 'わ', 'ゟ'};
    /**
     * Atributo de la clase que guarda el alfabeto sobre el que se va a
     * encriptar
     */
    ArrayList<Character> alfabeto;

    public MaquinaCripto() {
        this.alfabeto = new ArrayList<>();
    }

    public MaquinaCripto(char[] alfabeto) {
        this.alfabeto = new ArrayList<>();
        for (char c : alfabeto) {
            this.alfabeto.add(c);
        }
    }

    @Override
    public void EliminarAlfabeto() {
        this.alfabeto.clear();
    }

    @Override
    public boolean esVacio() {
        return this.alfabeto.isEmpty();
    }

    private int buscarPosicionAlfabeto(Character caracterBuscado,
            ArrayList<Character> alfabeto) throws IllegalArgumentException {
        for (int i = 0; i < alfabeto.size(); i++) {
            if (Objects.equals(caracterBuscado, alfabeto.get(i))) {
                return i;
            }
        }
        throw new IllegalArgumentException("El caracter " + "\'"
                + caracterBuscado + "\'" + " no se encuentra en el alfabeto");
    }

    @Override
    public String encriptadoTransposicionGrupos(String mensajeClaro,
            String clave) {
        int tamClave = clave.length();
        int cantSubMensajes
                = Math.ceilDiv(mensajeClaro.length(), clave.length());
        int[] reordenamiento = colocarOrdenes(clave);
        char[] mensajeEncriptado = new char[cantSubMensajes * tamClave];

        for (int i = 0; i < cantSubMensajes; i++) {
            for (int j = 0; j < tamClave; j++) {
                if (tamClave * i + j < mensajeClaro.length()) {
                    mensajeEncriptado[tamClave * i + reordenamiento[j]]
                            = mensajeClaro.charAt(tamClave * i + j);
                } else {
                    mensajeEncriptado[tamClave * i + reordenamiento[j]] = '@';
                }
            }
        }
        return String.valueOf(mensajeEncriptado);
    }

    @Override
    public String desencriptadoTransposicionGrupos(String mensajeEncriptado,
            String clave) {
        int tamClave = clave.length();
        int cantSubMensajes
                = Math.ceilDiv(mensajeEncriptado.length(), clave.length());
        int[] reordenamiento = colocarOrdenes(clave);
        String mensajeClaro = "";

        for (int i = 0; i < cantSubMensajes; i++) {
            for (int j = 0; j < tamClave; j++) {
                if (tamClave * i + j < mensajeEncriptado.length()) {
                    mensajeClaro += mensajeEncriptado.charAt(tamClave * i + reordenamiento[j]);
                }
            }
        }
        return mensajeClaro;
    }

    @Override
    public String encriptadoTransposicionSeries(String mensajeClaro) {
        int mLength = mensajeClaro.length();
        ArrayList<Integer> serie1 = serieNumerosPrimos(mLength);
        ArrayList<Integer> serie2 = serieNumerosParesNoPrimos(mLength);
        ArrayList<Integer> serie3 = serieNumerosImparesNoPrimos(mLength);
        String mensajeEncriptado = "";

        for (Integer index : serie1) {
            mensajeEncriptado += mensajeClaro.charAt(index - 1);
        }
        for (Integer index : serie2) {
            mensajeEncriptado += mensajeClaro.charAt(index - 1);
        }
        for (Integer index : serie3) {
            mensajeEncriptado += mensajeClaro.charAt(index - 1);
        }
        return mensajeEncriptado;
    }

    @Override
    public String desencriptadoTransposicionSeries(String mensajeEncriptado) {
        int mLength = mensajeEncriptado.length();
        ArrayList<Integer> serie1 = serieNumerosPrimos(mLength);
        ArrayList<Integer> serie2 = serieNumerosParesNoPrimos(mLength);
        ArrayList<Integer> serie3 = serieNumerosImparesNoPrimos(mLength);
        char[] mensajeClaro = new char[mLength];
        int i = 0;

        for (Integer index : serie1) {
            mensajeClaro[index - 1] = mensajeEncriptado.charAt(i);
            i++;
        }
        for (Integer index : serie2) {
            mensajeClaro[index - 1] = mensajeEncriptado.charAt(i);
            i++;
        }
        for (Integer index : serie3) {
            mensajeClaro[index - 1] = mensajeEncriptado.charAt(i);
            i++;
        }
        return String.valueOf(mensajeClaro);
    }

    @Override
    public String encriptadoTransposicionColumnas(String mensaje, String clave) {
        int[] ordenesColumnas = colocarOrdenes(clave);
        int filas = (int) Math.ceil(mensaje.length() * 1.0 / clave.length() * 1.0);
        int columnas = clave.length();
        char[] mensajeEncriptado = new char[columnas * filas];

        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                if (i + j * columnas < mensaje.length()) {
                    mensajeEncriptado[ordenesColumnas[i] * filas + j] = mensaje.charAt(i + j * columnas);
                } else {
                    mensajeEncriptado[ordenesColumnas[i] * filas + j] = alfabeto.get(0);
                }
            }
        }

        return toString(mensajeEncriptado);
    }

    @Override
    public String desencriptadoTransposicionColumnas(String mensaje, String clave) {
        int[] ordenesColumnas = colocarOrdenes(clave);
        String mensajeDesencriptado = "";
        int filas = (int) Math.ceil(mensaje.length() * 1.0 / clave.length() * 1.0);
        int columnas = clave.length();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                mensajeDesencriptado += mensaje.charAt(ordenesColumnas[j] * filas + i);
            }
        }

        return mensajeDesencriptado;
    }

    @Override
    public String encriptadoTransposicionFilas(String mensaje, String clave) {
        int[] ordenesfilas = colocarOrdenes(clave);
        int columnas = (int) Math.ceil(mensaje.length() * 1.0 / clave.length() * 1.0);
        int filas = clave.length();
        char[] mensajeEncriptado = new char[filas * columnas];

        for (int j = 0; j < filas; j++) {
            for (int i = 0; i < columnas; i++) {
                if (j + i * filas < mensaje.length()) {
                    mensajeEncriptado[ordenesfilas[j] * columnas + i] = mensaje.charAt(j + i * filas);
                } else {
                    mensajeEncriptado[ordenesfilas[j] * columnas + i] = alfabeto.get(0);
                }
            }
        }

        return toString(mensajeEncriptado);
    }

    @Override
    public String desencriptadoTransposicionFilas(String mensaje, String clave) {
        int[] ordenesColumnas = colocarOrdenes(clave);
        String mensajeDesencriptado = "";
        int columnas = (int) Math.ceil(mensaje.length() * 1.0 / clave.length() * 1.0);
        int filas = clave.length();

        for (int j = 0; j < columnas; j++) {
            for (int i = 0; i < filas; i++) {
                mensajeDesencriptado += mensaje.charAt(ordenesColumnas[i] * columnas + j);
            }
        }

        return mensajeDesencriptado;
    }

    @Override
    public String encriptadoTransposicionZigZag(String mensaje, int filas) {
        char[][] mzz = new char[filas][mensaje.length()];
        boolean bBajada = false;
        String me = "";
        int fil = 0;
        int col = 0;

        //colocamos los caracteres en la matriz 
        for (int i = 0; i < mensaje.length(); i++) {
            if (fil == 0 || fil == filas - 1) {
                bBajada = !bBajada;
            }
            mzz[fil][col++] = mensaje.charAt(i);
            if (bBajada) {
                fil++;
            } else {
                fil--;
            }
        }

        //escribimos el mensaje encriptado
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < mensaje.length(); j++) {
                if (mzz[i][j] != Character.MIN_VALUE) {
                    me += String.valueOf(mzz[i][j]);
                }
            }
        }

        return me;
    }

    @Override
    public String desencriptadoTransposicionZigZag(String mensaje, int filas) {
        char[][] mzz = new char[filas][mensaje.length()];
        boolean bBajada = true;
        int fil = 0;
        int col = 0;
        int index = 0;
        String mensajeDesencriptado = "";

        for (int i = 0; i < mensaje.length(); i++) {
            if (fil == 0) {
                bBajada = true;
            }
            if (fil == filas - 1) {
                bBajada = false;
            }
            mzz[fil][col++] = '*';
            if (bBajada) {
                fil++;
            } else {
                fil--;
            }
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < mensaje.length(); j++) {
                if (mzz[i][j] == '*' && index < mensaje.length()) {
                    mzz[i][j] = mensaje.charAt(index++);
                }
            }
        }

        fil = 0;
        col = 0;
        for (int i = 0; i < mensaje.length(); i++) {
            if (fil == 0) {
                bBajada = true;
            }
            if (fil == filas - 1) {
                bBajada = false;
            }
            if (mzz[fil][col] != '*') {
                mensajeDesencriptado += mzz[fil][col++];
            }
            if (bBajada) {
                fil++;
            } else {
                fil--;
            }
        }
        return mensajeDesencriptado;
    }

    @Override
    public String encriptadoSustitucionDesplazamientoGenerico(String mensaje, 
            int desplazamiento, int decimacion, String clave) {
        if(desplazamiento < 0){
            throw new IllegalArgumentException("El desplazamiento no puede"
                    + "ser negativo");
        }
        if(!coprimos(decimacion, alfabeto.size())){
            throw new IllegalArgumentException("La decimacion debe ser "
                    + "coprimo con la longitud del alfabeto");
        }
        String mensajeEncriptado = "";
        ArrayList<Character> alfabetoConClave = alfabetoConClave(clave);

        for (int i = 0; i < mensaje.length(); i++) {
            mensajeEncriptado += alfabetoConClave.get((decimacion
                    * posicionCaracter(mensaje.charAt(i),
                            alfabetoConClave) + desplazamiento)
                    % alfabetoConClave.size());
        }
        return mensajeEncriptado;
    }

    @Override
    public String desencriptadoSustitucionDesplazamientoGenerico(String mensaje,
            int desplazamiento, int decimacion, String clave) {
        if(desplazamiento < 0){
            throw new IllegalArgumentException("El desplazamiento no puede"
                    + "ser negativo");
        }
        if(!coprimos(decimacion, alfabeto.size())){
            throw new IllegalArgumentException("La decimacion debe ser "
                    + "coprimo con la longitud del alfabeto");
        }
        String mensajeDesencriptado = "";
        int w;
        ArrayList<Character> alfabetoConClave = alfabetoConClave(clave);

        for (int i = 0; i < mensaje.length(); i++) {
            w = modInverse(decimacion, alfabetoConClave.size()) * (posicionCaracter(mensaje.charAt(i), alfabetoConClave) - desplazamiento);
            while (w < 0) {
                w += alfabetoConClave.size();
            }
            mensajeDesencriptado += alfabetoConClave.get(w % alfabetoConClave.size());
        }
        return mensajeDesencriptado;
    }

    @Override
    public String encriptadoSustitucionPoliAlfabetica(String mensajeClaro) {
        ArrayList<Character> alfabeto1
                = alfabetoDerivado("", 2, 1);
        ArrayList<Character> alfabeto2
                = alfabetoDerivado("", 4, 1);
        ArrayList<Character> alfabeto3
                = alfabetoDerivado("", 16, 1);
        int pos;
        String mensajeCifrado = "";
        int i = 0;

        while (i < mensajeClaro.length()) {
            pos = posicionCaracter(mensajeClaro.charAt(i),
                    this.alfabeto);
            mensajeCifrado += alfabeto1.get(pos);
            i++;
            if (i < mensajeClaro.length()) {
                pos = posicionCaracter(mensajeClaro.charAt(i),
                        this.alfabeto);
                mensajeCifrado += alfabeto2.get(pos);
                i++;
            }
            if (i < mensajeClaro.length()) {
                pos = posicionCaracter(mensajeClaro.charAt(i),
                        this.alfabeto);
                mensajeCifrado += alfabeto3.get(pos);
                i++;
            }
        }

        return mensajeCifrado;
    }

    @Override
    public String desencriptadoSustitucionPoliAlfabetica(String mensajeEncriptado) {
        ArrayList<Character> alfabeto1
                = alfabetoDerivado("", 2, 1);
        ArrayList<Character> alfabeto2
                = alfabetoDerivado("", 4, 1);
        ArrayList<Character> alfabeto3
                = alfabetoDerivado("", 16, 1);
        int pos;
        String mensajeClaro = "";
        int i = 0;

        while (i < mensajeEncriptado.length()) {
            pos = posicionCaracter(mensajeEncriptado.charAt(i), alfabeto1);
            mensajeClaro += this.alfabeto.get(pos);
            i++;
            if (i < mensajeEncriptado.length()) {
                pos = posicionCaracter(mensajeEncriptado.charAt(i),
                        alfabeto2);
                mensajeClaro += this.alfabeto.get(pos);
                i++;
            }
            if (i < mensajeEncriptado.length()) {
                pos = posicionCaracter(mensajeEncriptado.charAt(i),
                        alfabeto3);
                mensajeClaro += this.alfabeto.get(pos);
                i++;
            }
        }

        return mensajeClaro;
    }

    @Override
    public String encriptadoSustitucionHomofonosPrimerOrden(String mensaje,
            int[][] tabla) {
        int pos;
        String mC = "";
        for (int i = 0; i < mensaje.length(); i++) {
            pos = posicionCaracter(mensaje.charAt(i), alfabeto);
            int x = (int) Math.floor(Math.random() * (tabla[pos].length - 1));
            mC += tabla[pos][x] + "-";
        }
        mC = mC.substring(0, mC.length() - 1);
        return mC;
    }

    @Override
    public String desencriptadoSustitucionHomofonosPrimerOrden(String mensaje,
            int[][] tabla) {
        char c;
        String digN;
        int i = 0;
        String mC = "";

        while (i < mensaje.length()) {
            digN = "";
            c = mensaje.charAt(i);
            while ((c != '-') && i < mensaje.length()) {
                digN += c;
                i++;
                if (i < mensaje.length()) {
                    c = mensaje.charAt(i);
                }
            }
            if (!digN.equals("")) {
                mC += caracterAlfabeto(Integer.parseInt(digN), tabla);
            }
            i++;
        }
        return mC;
    }

    @Override
    public String encriptadoSustitucionHomofonosOrdenMayor(int[][] tabla,
            String mensajeCorrecto, String mensajeFalso, boolean enFuncionFilas) {
        if (mensajeCorrecto.length() > mensajeFalso.length()) {
            throw new IllegalArgumentException("El mensaje falso es mas corto"
                    + " que el mensaje correcto por favor ingresar una mensaje"
                    + " falso de la misma longitud o mayor que el correcto");
        }

        String me = "";
        int posFil;
        int posCol;
        if (enFuncionFilas) {
            for (int i = 0; i < mensajeCorrecto.length(); i++) {
                posFil = posicionCaracter(mensajeCorrecto.charAt(i),
                        this.alfabeto);
                posCol = posicionCaracter(mensajeFalso.charAt(i),
                        this.alfabeto);

                me += tabla[posFil][posCol] + "-";
            }
        } else {
            for (int i = 0; i < mensajeCorrecto.length(); i++) {
                posCol = posicionCaracter(mensajeCorrecto.charAt(i),
                        this.alfabeto);
                posFil = posicionCaracter(mensajeFalso.charAt(i),
                        this.alfabeto);

                me += tabla[posFil][posCol] + "-";
            }
        }
        me = me.substring(0, me.length() - 1);
        return me;
    }

    @Override
    public String desencriptadoSustitucionHomofonosOrdenMayor(int[][] tabla,
            String mensajeEncriptado, boolean enFuncionFilas) {
        String mC = "";
        String n;

        for (int i = 0; i < mensajeEncriptado.length(); i++) {
            n = "";
            while (i < mensajeEncriptado.length() && mensajeEncriptado.charAt(i) != '-') {
                if (i < mensajeEncriptado.length()) {
                    n += mensajeEncriptado.charAt(i);
                }
                i++;
            }
            if (!n.equals("")) {
                mC += this.alfabeto.get(posicionTabla(Integer.parseInt(
                        n), enFuncionFilas, tabla));
            }
        }
        return mC;
    }

    @Override
    public String encriptadoSustitucionPolialfabeticosPeriodicos(String mensaje,
            String clave) {
        String mensajeEncriptado = "";
        clave = generarClaveVigenere(clave, mensaje.length());

        for (int i = 0; i < mensaje.length(); i++) {
            int x = posicionCaracter(mensaje.charAt(i), this.alfabeto);
            int y = posicionCaracter(clave.charAt(i), this.alfabeto);
            mensajeEncriptado += (this.alfabeto.get((x + y) % this.alfabeto.size()));
        }
        return mensajeEncriptado;
    }

    @Override
    public String desencriptadoSustitucionPolialfabeticosPeriodicos(String mensaje,
            String clave) {
        String mensajeEncriptado = "";
        clave = generarClaveVigenere(clave, mensaje.length());

        for (int i = 0; i < mensaje.length(); i++) {
            int x = posicionCaracter(mensaje.charAt(i), this.alfabeto);
            int y = posicionCaracter(clave.charAt(i), this.alfabeto);
            mensajeEncriptado += (this.alfabeto.get((x - y + this.alfabeto.size()) % this.alfabeto.size()));
        }
        return mensajeEncriptado;
    }

    private ArrayList<Character> alfabetoDerivado(String clave,
            int desplazamiento, int decimacion) {
        ArrayList<Character> alfabetoDesplazado = new ArrayList<>();
        ArrayList<Character> alfabetoAux = new ArrayList<>(this.alfabeto);

        if (!clave.equals("")) {
            alfabetoDesplazado = new ArrayList<>(this.alfabeto);

            for (int i = 0; i < clave.length(); i++) {
                alfabetoAux.add(clave.charAt(i));
                alfabetoDesplazado.remove(clave.charAt(i));
            }
            for (Character character : alfabetoDesplazado) {
                alfabetoAux.add(character);
            }
        }
        for (int i = 0; i < alfabetoAux.size(); i++) {
            alfabetoDesplazado.add(alfabetoAux.get((decimacion * i
                    + desplazamiento) % alfabetoAux.size()));
        }
        return alfabetoDesplazado;
    }

    private int[] colocarOrdenes(String clave) {
        int[] ordenColumnas = new int[clave.length()];
        int[] ordenColumnasR = new int[clave.length()];
        int menor;
        int indiceObservado = 0;
        int b = 0;

        for (int i = 0; i < ordenColumnas.length; i++) {
            ordenColumnas[i] = buscarPosicionAlfabeto(clave.charAt(i), alfabeto);
            ordenColumnasR[i] = ordenColumnas[i];
        }

        while (b < clave.length()) {
            menor = this.alfabeto.size();
            for (int i = 0; i < ordenColumnasR.length; i++) {
                if (ordenColumnasR[i] < menor) {
                    menor = ordenColumnasR[i];
                    indiceObservado = i;
                }
            }
            ordenColumnasR[indiceObservado] = this.alfabeto.size();
            ordenColumnas[indiceObservado] = b;
            b++;
        }
        return ordenColumnas;
    }

    private String toString(char[] mensajeEncriptado) {
        String mensaje = "";
        for (char c : mensajeEncriptado) {
            mensaje += String.valueOf(c);
        }
        return mensaje;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Character> getAlfabeto() {
        return alfabeto;
    }

    private boolean esPrimo(int n, int i) {
        if (n <= 2) {
            return n == 1 || n == 2;
        }
        if (n % i == 0) {
            return false;
        }
        if (i * i > n) {
            return true;
        }

        return esPrimo(n, i + 1);
    }

    private ArrayList<Integer> serieNumerosPrimos(int n) {
        ArrayList<Integer> serieNumerosPrimos = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (esPrimo(i, 2)) {
                serieNumerosPrimos.add(i);
            }
        }
        return serieNumerosPrimos;
    }

    private ArrayList<Integer> serieNumerosParesNoPrimos(int n) {
        ArrayList<Integer> serieNumerosPrimos = new ArrayList<>();
        for (int i = 4; i <= n; i += 2) {
            if (!esPrimo(i, 2)) {
                serieNumerosPrimos.add(i);
            }
        }
        return serieNumerosPrimos;
    }

    private ArrayList<Integer> serieNumerosImparesNoPrimos(int n) {
        ArrayList<Integer> serieNumerosPrimos = new ArrayList<>();
        for (int i = 9; i <= n; i += 2) {
            if (!esPrimo(i, 2)) {
                serieNumerosPrimos.add(i);
            }
        }
        return serieNumerosPrimos;
    }

    private int posicionCaracter(char caracter, ArrayList<Character> alfabeto)
            throws IllegalArgumentException {
        for (int i = 0; i < alfabeto.size(); i++) {
            if (alfabeto.get(i) == caracter) {
                return i;
            }
        }
        throw new IllegalArgumentException("El caracter: " + caracter + " no"
                + "se encruentra en el alfabeto");
    }

    private int modInverse(int a, int m) {
        for (int i = 1; i < m; i++) {
            if (((a % m) * (i % m)) % m == 1) {
                return i;
            }
        }
        return 1;
    }

    private ArrayList<Character> alfabetoConClave(String clave) {
        ArrayList<Character> alfabetoConClave = new ArrayList<>();
        ArrayList<Character> aux = new ArrayList<>(this.alfabeto);
        Character c;

        for (int i = 0; i < clave.length(); i++) {
            c = clave.charAt(i);
            alfabetoConClave.add(c);
            aux.remove(c);
        }

        for (int i = 0; i < aux.size(); i++) {
            alfabetoConClave.add(aux.get(i));
        }

        return alfabetoConClave;
    }

    public static int[][] generarHomofonos(int cantHomofonos,
            ArrayList<Character> alfabeto) {
        final int LIM_CUENTA = cantHomofonos * alfabeto.size();
        int[][] mH = new int[alfabeto.size()][cantHomofonos];
        ArrayList<Integer> coleccion = new ArrayList();
        int iRandom;

        for (int i = 0; i < LIM_CUENTA; i++) {
            coleccion.add(i);
        }

        for (int[] mH1 : mH) {
            for (int j = 0; j < mH1.length; j++) {
                iRandom = (int) Math.floor(Math.random() * coleccion.size());
                mH1[j] = coleccion.get(iRandom);
                coleccion.remove(iRandom);
            }
        }

        return mH;
    }

    private Character caracterAlfabeto(int homofono, int[][] tabla) {
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[0].length; j++) {
                if (homofono == tabla[i][j]) {
                    return this.alfabeto.get(i);
                }
            }
        }
        throw new IllegalArgumentException("El homofono no se encuentra en "
                + "la tabla");
    }

    public static int sigNumero(String mensajeEncriptado, Integer j) {
        String n = "";
        do {
            n += mensajeEncriptado.charAt(j);
            j++;
        } while (mensajeEncriptado.charAt(j) != '-' && j < mensajeEncriptado.length());
        return Integer.parseInt(n);
    }

    private int posicionTabla(int n, boolean enFuncionDelafila, int[][] tabla) {
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                if (n == tabla[i][j]) {
                    if (enFuncionDelafila) {
                        return i;
                    } else {
                        return j;
                    }
                }
            }
        }
        throw new IllegalArgumentException("No se encuentra el numero en "
                + "la tabla");
    }

    private String generarClaveVigenere(String clave, int tamaño) {
        String claveVigenere = clave;
        while (claveVigenere.length() < tamaño) {
            claveVigenere += clave;
        }
        return claveVigenere.substring(0, tamaño);
    }

    private boolean coprimos(int a, int b) {
        return mcd(a, b) == 1;
    }

    private int mcd(int a, int b) {
    if (b == 0) return a;
    return mcd(b, a % b);
    }
}
