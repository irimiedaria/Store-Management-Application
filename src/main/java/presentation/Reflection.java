package presentation;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

/**
 * clasa creeaza formatul de tabel al bazei de date care va fi vizualizat in interfata
 */

public class Reflection {
    /**
     * metoda generica
     * itereaza asupra fiecarui element din lista (data ca si parametru)
     * pentru fiecare element creeaza un sir de caractere
     * a doua bucla for ofera acces pt campurile private
     * daca este prima iteratie (determinata de flag-ul firstIteration), adauga o noua coloana la tabel (model) cu numele field-ului
     * valoarea fiel-ului este convertita intr-un string si stocata in sirul data la indexul i, care este incrementat dupa
     * la final, sirul data este adaugatat ca un rand nou la tabel (model)
     * @param list
     * @return model se returneaza tabelul format
     * @param <T>
     */
    public static <T> DefaultTableModel getTableModel(List<T> list) {
        DefaultTableModel model = new DefaultTableModel();

        int i = 0;
        Boolean firstIteration = true;

        for (Object object : list) {
            String[] data = new String[100];

            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(object);
                    if (firstIteration == true)
                        model.addColumn(field.getName());
                    data[i] = value.toString();
                    i++;

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(data);
            i = 0;
            firstIteration = false;
        }
        return model;
    }
}
