package Enum;

public enum Animal {
    DOG("Пес"), CAT("Кот"), FROG("Лягик");
    String translation;
    Animal(String translation){
        this.translation = translation;
    }
    String getTranslation(){
        return translation;
    }
}
