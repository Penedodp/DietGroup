package com.campusdual.ejercicio4;

import com.campusdual.ejemplos.alimentos.Food;

import java.util.ArrayList;
import java.util.List;

/*
* Escribe una clase dieta, que teniendo en cuenta una serie de alimentos, vaya sumando cantidades en gramos y calcule cuentas calorías, carbohidratos, proteinas y grasas genera la ingesta
La clase dieta tiene que tener las siguientes funcionalidades:
	-Diet(): crea una dieta sin límite de calorías
	-Diet(Integer maxCalories): crea una dieta con un máximo de calorías, en cuanto se supera ese máximo cuando se adjunta un alimento se despliega un error
	-Diet(Integer maxFats, Integer maxCarbs, Integer maxProtein): crea una dieta con un máximo de estos tres valores, si se supera alguno de ellos cuando se adjunta un alimento se indicara cual y desplegará un error
	-Diet(Boolean women, Integer age, Integer height, Integer weight): Calcula el metabolismo basal de la persona y lo asigna como máximo de calorías en la dieta
		--CALCULAR METABOLISMO BASAL
			E = edad
			A = altura en centímetros
			P = peso en kilos

			Fórmula Hombres: TMB = 10P + 6,25A – 5E + 5
			Fórmula Mujeres: TMB = 10P + 6,25A – 5E – 161
	-addFood(Food,Integer): agrega un alimento y una cantidad en gramos
	-getTotalCalories(): devuelve el total de calorías
	-getTotalCarbs(): devuelve el total de carbohidratos
	-getTotalFats(): devuelve el total de grasas
	-getTotalProtein(): devuelve el total de proteinas
*
* */
public class Diet {

    private Integer maxCalories;
    private List<Food> foodlist;

    private List<Integer> weight;

    private Integer maxProteins;
    private Integer maxCarbs;
    private Integer maxFats;

    public Diet() {
        this.foodlist = new ArrayList<Food>();
        this.weight = new ArrayList<Integer>();

    }

    public Diet(int maxProteins, int maxCarbos, int maxFats){
        this.maxProteins = 0;
        this.maxFats = 0;
        this.maxCarbs = 0;
    }


    public Integer getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
    }

    public Integer getMaxProteins() {
        return maxProteins;
    }

    public void setMaxProteins(Integer maxProteins) {
        this.maxProteins = maxProteins;
    }

    public Integer getMaxCarbos() {
        return maxCarbs;
    }

    public void setMaxCarbos(Integer maxCarbos) {
        this.maxCarbs = maxCarbos;
    }

    public Integer getMaxFats() {
        return maxFats;
    }

    public void setMaxFats(Integer maxFats) {
        this.maxFats = maxFats;
    }

    public Diet(Integer maxCalories) {
        this.foodlist = new ArrayList<Food>();
        this.weight = new ArrayList<Integer>();
        this.maxCalories = maxCalories;
    }


    public void addFood(Food food, Integer weight) {
        this.foodlist.add(food);
        this.weight.add(weight);
    }

    public double getTotalCalories() {
        double totalCalories = 0;
        for (int i = 0; i < this.foodlist.size(); i++) {
            totalCalories += foodlist.get(i).getCalories(weight.get(i));
        }
        return totalCalories;
    }

    public double getTotalCarbs() {
        double totalCarbs = 0;
        for (int i = 0; i < this.foodlist.size(); i++) {
            totalCarbs += foodlist.get(i).getCarbos() * this.weight.get(i) / 100;
        }
        return totalCarbs;
    }

    public double getTotalFats() {
        double totalFats = 0;
        for (int i = 0; i < this.foodlist.size(); i++) {
            totalFats += foodlist.get(i).getFats() * this.weight.get(i) / 100;
        }
        return totalFats;
    }

    public double getTotalProteins() {
        double totalProteins = 0;
        for (int i = 0; i < this.foodlist.size(); i++) {
            totalProteins += foodlist.get(i).getProteins() * this.weight.get(i) / 100;
        }
        return totalProteins;
    }

    public Diet getMacro(int maxProteins,int maxCarbs, int maxFats) {
        Diet macro = new Diet(maxProteins,maxCarbs,maxFats);

        for (int i = 0; i < this.foodlist.size(); i++) {
            int porPeso = this.weight.get(i) / 100;
            int foodProteins = this.foodlist.get(i).getProteins();
            int foodCarbs = this.foodlist.get(i).getCarbos();
            int foodFats = this.foodlist.get(i).getFats();

            macro.addFood(this.foodlist.get(i), this.weight.get(i));

            macro.setMaxProteins(foodProteins * porPeso);
            macro.setMaxCarbos(foodCarbs *  porPeso);
            macro.setMaxFats(foodFats * porPeso);
        }
        return macro;

    }
    public void showDietInfo() {
        System.out.println("----- -- Informacion de su dieta ----- -- :");
        System.out.println("Calorias maximas: -    " + maxCalories);
        System.out.println("Total de calorias:  " + getTotalCalories());
        System.out.println("Proteinas maximas: -    " + maxProteins);
        System.out.println("Total de proteinas:  " + getTotalProteins());
        System.out.println("Carbohidratos maximos: -    " + maxCarbs);
        System.out.println("Total de carbohidratos:  " + getTotalCarbs());
        System.out.println("Grasas maximas: -" + maxFats);
        System.out.println("Total de grasas:  " + getTotalFats());

        System.out.println("----- -- Alimentos en la dieta -- -----:");
        for (int i = 0; i < foodlist.size(); i++) {
            System.out.println("Alimento:  " + foodlist.get(i).getName());
            System.out.println("Peso:  " + weight.get(i) + " grams");
            System.out.println("Calorias:  " + foodlist.get(i).getCalories(weight.get(i)));
            System.out.println("Proteinas:  " + (foodlist.get(i).getProteins() * weight.get(i) / 100) + " grams");
            System.out.println("Carbohidratos:  " + (foodlist.get(i).getCarbos() * weight.get(i) / 100) + " grams");
            System.out.println("Grasas:  " + (foodlist.get(i).getFats() * weight.get(i) / 100) + " grams");
            System.out.println("---------------");
        }
    }
}












