package Objetos;

import java.lang.reflect.Array;

public enum Tipo {
    ESCALADA,
    TIRO_CON_ARCO,
    SENDERISMO,
    TIROLINA,
    RAPPEL,
    CICLISMO,
    CANOA,
    RAFTING,
    PAINTBALL,
    ORIENTAICION;

    public static boolean validaTipo(String tipo){
        Tipo[] tipos = Tipo.values();

        for (int i = 0; i < tipos.length; i++) {
            if (tipos[i].name().equalsIgnoreCase(tipo)) {
                return true;
            }
        }
        return false;
    }
}
