package com.campusdual.ejercicio4;

import com.campusdual.ejemplos.alimentos.Food;
import sun.font.AttributeValues;

import java.util.Random;
import java.util.Scanner;

/*
* --Escribe un programa que utilice la clase Dieta y despliegue un menú donde puedas añadir alimentos. El menú tendrá las siguientes opciones.
	-1. Crear/reiniciar dieta: crea o remplaza la dieta inicial
		-a. Sin limite
		-b. Por calorías
		-c. Por macronutrientes
		-d. Por datos personales
	-2. Mostrar información: muestra calorías y macronutrientes de la dieta
	-3. Agregar alimento: agrega un alimento a la dieta actual y añade ese alimento a la lista de alimentos disponible
		-a. Nuevo alimento
		-b. Alimento existente
	-4. Salir
* */
public class Menu {
    private Diet dieta;

    public Menu() {

    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("----- Menú de Dieta -----");
            System.out.println("1. Crear/reiniciar dieta");
            System.out.println("2. Mostrar información");
            System.out.println("3. Agregar alimento");
            System.out.println("4. Salir");
            System.out.print("Elija una opción (1/2/3/4): ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("a. Sin límite");
                    System.out.println("b. Por calorías");
                    System.out.println("c. Por macronutrientes");
                    System.out.println("d. Por datos personales");
                    System.out.print("Elija una opción (a/b/c/d): ");
                    char subopcion = scanner.nextLine().charAt(0);
                    switch (subopcion) {
                        case 'a':
                            dieta = new Diet();
                            agregarAlimentos();
                            break;
                        case 'b':
                            System.out.println("Ingrese el numero de calorias maximo para su dieta: ");
                            int establecerCalorias = scanner.nextInt();
                            dieta = new Diet(establecerCalorias);
                            System.out.println("Se ha creado una dieta con un maximo de " + establecerCalorias + " calorias.");

                            while (dieta.getTotalCalories() < establecerCalorias) {
                                agregarAlimentos();
                            }
                            double totalCaloriasDieta = dieta.getTotalCalories();
                            if (totalCaloriasDieta > dieta.getMaxCalories()) {
                                System.out.println("El último alimento supera el límite de calorías máximas.");
                                System.out.println("Por favor, vuelva a ingresar el peso del último alimento.");
                                System.out.print("Nuevo peso en gramos: ");
                                establecerCalorias = scanner.nextInt();
                                scanner.nextLine();
                                Food alimento = new Food();
                                alimento.setWeight(establecerCalorias);
                                dieta.addFood(alimento, establecerCalorias);

                                totalCaloriasDieta = dieta.getTotalCalories();
                                System.out.println("Las calorias actuales son : " + totalCaloriasDieta);
                                System.out.println("Por unas pocas calorias mas no pasa nada wey xD");
                                // AQUI TENGO QUE ARREGLAR EL FINAL!

                            }
                            System.out.println("Se ha alcanzado el límite máximo de calorías.");
                            break;
                        case 'c':
                            System.out.print("Ingrese el número máximo de proteínas para su dieta: ");
                            int proteinsMax = scanner.nextInt();
                            System.out.print("Ingrese el número máximo de carbohidratos para su dieta: ");
                            int carbsMax = scanner.nextInt();
                            System.out.print("Ingrese el número máximo de grasas para su dieta: ");
                            int fatsMax = scanner.nextInt();

                            dieta = new Diet();
                            dieta = dieta.getMacro(proteinsMax, carbsMax, fatsMax);

                            while (true) {
                                agregarAlimentos();

                                if (dieta.getTotalProteins() >= proteinsMax || dieta.getTotalCarbs() >= carbsMax || dieta.getTotalFats() >= fatsMax) {
                                    System.out.println("Se ha alcanzado uno de los límites máximos de proteínas, carbohidratos o grasas.");
                                    System.out.println("Volviendo al menú principal.");
                                    break;
                                }
                            }
                            break;

                        case 'd':
                            //dieta = new DietaPorDatosPersonales();
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }
                    break;
                case 2:
                    if (dieta != null) {
                        dieta.showDietInfo();
                    } else {
                        System.out.println("No hay dieta creada.");
                    }
                    break;
                case 3:
                    if (dieta != null) {
                        agregarAlimentos();
                    } else {
                        System.out.println("No hay dieta creada.");
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 4);
        scanner.close();
    }



    public void agregarAlimentos(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("a. Nuevo alimento");
        System.out.println("b. Alimento existente");
        System.out.print("Elija una opción (a/b): ");
        char agregarAlimento = scanner.nextLine().charAt(0);
        if (agregarAlimento == 'a') {
            System.out.print("Ingrese el nombre del alimento: ");
            String nombreAlimento = scanner.nextLine();

            System.out.print("Ingrese la cantidad en gramos: ");
            int cantidadGramos = scanner.nextInt();
            scanner.nextLine();

            Random random = new Random();
            int carbohidratos = random.nextInt(19);
            int proteinas = random.nextInt(17);
            int grasas = random.nextInt(20);
            Food alimento = new Food(nombreAlimento, carbohidratos, proteinas, grasas);
            dieta.addFood(alimento, cantidadGramos);

            System.out.println(" Informacion de alimento: \n");
            System.out.println("Nombre: " + alimento.getName() + " \n");
            System.out.println("Grasas : " + alimento.getFats() + " \n ");
            System.out.println("Proteinas : " + alimento.getProteins() + " \n ");
            System.out.println("Carbos : " + alimento.getCarbos() + " \n ");
            System.out.println("Calorias totales del alimento : " + alimento.getCalories(cantidadGramos));

        } else if (agregarAlimento == 'b') {
            // Agregar alimento existente a la dieta aaaaaa

        } else {
            System.out.println("Opción no válida");
        }

    }


}
