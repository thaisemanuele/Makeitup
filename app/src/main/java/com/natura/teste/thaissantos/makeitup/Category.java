package com.natura.teste.thaissantos.makeitup;

/**
 * Created by thais.santos on 09/04/2017.
 */

public enum Category {

    PO      ("Pó"),
    BATM    ("Batom"),
    GLSS    ("Gloss"),
    BASE    ("Base"),
    BLSH    ("Blush"),
    CORR    ("Corretivo"),
    SOMB    ("Sombra"),
    NOTFOUND ("Não encontrada");
    ;

    private String description;

    Category(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public Category getByDescription(String description){
        for (Category category:Category.values()
             ) {
            if (category.getDescription().equalsIgnoreCase(description)){
                return category;
            }
        }
        return Category.NOTFOUND;
    }
}
