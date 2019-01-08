package com.bilalmoreno.malagasport.data.db.repository;

import com.bilalmoreno.malagasport.data.db.model.Machine;

import java.util.ArrayList;

public class MachineRepository {
    private static MachineRepository repository;

    static {
        repository = new MachineRepository();
    }

    private ArrayList<Machine> machines;

    private MachineRepository() {
        machines = new ArrayList<>();
        inicializar();
    }

    public static MachineRepository getRepository() {
        return repository;
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }

    public ArrayList<Machine> getMaquinas(ArrayList<Integer> idsMaquinas) {
        ArrayList<Machine> resultado = new ArrayList<>();
        for (int idMaquina :
                idsMaquinas) {
            resultado.add(getMaquina(idMaquina));
        }
        return resultado;
    }

    private void inicializar() {
        //TODO Inicializar MachineRepository
        add(new Machine(1, "Arco para brazos", 2, "Mejorar limitaciones de la articulación del hombro. Aumentar flexibilidad de la muñeca y movilidad del hombro", "Colocados debajo del arco, perpendicularmente a éste, con la espalda erguida y piernas separadas agarramos la anilla con una mano y la desplazamos desde un extremo a otro; repetimos con la otra mano. Cambiamos de posición, para situarnos paralelos al arco, y realizamos el ejercicio con las dos manos a la vez.", "Se debe abandonar el ejercicio si aparecen mareos o dolor de cuello.Los movimientos deben ser lentos y controlados."));
        add(new Machine(2, "Banco", 1, "Sentarse", "Sentadillas arriba y abajo.", "Se pueden cargar los anteebrazos"));
        add(new Machine(3, "Barra a media altura", 3, "Fortalecer los gemelos.", "Coloque las manos sobre la barra y eleve los talones. Repita el ejercicio varias veces.", "Agarrar firmemente la barra."));
        add(new Machine(4, "Barra giro muñecas", 2, "Fortalecer los músculos de antebrazos y manos.", "Colóquese frente al aparato con las manos cogidas a la barra. Comience el movimiento girando simultáneamente las muñecas arriba y abajo. Como variante, puede realizar un movimiento alternativo, mientras una muñeca sube, la otra baja.", "Los movimientos deben ser lentos y controlados."));
        add(new Machine(5, "Barras paralelas verticales", 4, "Fortalecer los músculos de glúteos y muslos.", "Colóquese entre las dos barras con las manos agarradas a los asideros a la altura del pecho. Flexione sus rodillas y vuelva a ponerse erguido, sin levantar los talones del suelo ni desplazar las manos por la barra.", "Mantener la espalda recta durante la ejecución.No flexionar excesivamente las rodillas."));
        add(new Machine(6, "Barras paralelas", 4, "Mejorar la postura.Mejorar la movilidad de las articulaciones de las piernas.Mejorar la flexibilidad general.", "De pie, cogidos a las barras, nos desplazamos de distintas formas: - Agachándonos cada 2 ó 3 pasos, - Elevando rodillas alternativamente.- Poniéndonos de puntillas.- Elevando una rodilla y soltando la mano contraria.", "Vigilar el correcto apoyo de los pies.Evitar arquear excesivamente la espalda."));
        add(new Machine(7, "Base inestable", 2, "Mejorar el equilibrio y la coordinación.", "Súbase a la plataforma, agarrándose a la barra, y haga desplazar la base con su propio peso, a derecha e izquierda.", "Tenga cuidado a la hora de acceder o abandonar el aparato, por posibles desequilibrios."));
        add(new Machine(8, "Bicicleta doble de pies y manos", 1, "Aumentar la resistencia de los miembros inferiores y superiores.Mejorar la amplitud articular en la rodilla.", "Siéntese en el banco, coloque ambos pies sobre los pedales y las manos sobre los asideros. Pedalee de manera coordinada hacia delante.", "Los movimientos deben ser lentos y controlados."));
        add(new Machine(9, "Disco de dedos y muñecas", 2, "Aumentar, o mantener, la flexibilidad de muñecas y dedos.", "Coja el disco con los dedos de una mano y hágalo girar a derecha e izquierda. Primero con una mano y luego con la otra.", "Los movimientos deben ser lentos y controlados."));
        add(new Machine(10, "Disco de muñecas", 1, "Aumentar, o mantener, la flexibilidad de las muñecas.", "Sitúese frente al disco y, agarrándolo con una mano, gire la muñeca hacia la derecha y hacia la izquierda. Repita con la otra mano.", "Los movimientos deben ser lentos y controlados."));
    }

    private void add(Machine machine) {
        machines.add(machine);
    }

    public Machine getMaquina(int idMaquina) {
        Machine machineBuscada = new Machine(idMaquina);
        for (Machine machine :
                machines) {
            if (machine.equals(machineBuscada)) {
                return machine;
            }
        }
        return null;
    }
}
