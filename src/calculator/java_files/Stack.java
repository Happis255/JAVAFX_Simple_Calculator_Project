package calculator.java_files;

/* Stos - reprezentacja stosu wykorzystywanego do wykonywania obliczeń ONP */
public class Stack {

    /* Przechowywany element na stosie
    *  @data - przechowywana wartość
    *  @prev - element poprzedzający dany element stosu */
    private static class StackItem {
        String value;
        StackItem prev;
    }

    /* Element odłożony na górze stosu */
    private StackItem stackTop;

    /* Konstruktor stosu - stos pusty, ustaw null */
    public Stack() {
        stackTop = null;
    }

    /* Sprawdza, czy na stosie jest odłożony element */
    public boolean isEmpty() {
        return stackTop == null;
    }

    /* Dodaje element na stos */
    public void push(String str) {

        StackItem newItem = new StackItem();
        newItem.value = str;

        /* Albo dodajemy element na górę stosu pustego, albo na kolejny element */
        if(isEmpty()){
            stackTop = newItem;
            stackTop.prev = null;
        } else {
            newItem.prev = stackTop;
            stackTop = newItem;
        }
    }

    /* Zwraca wartość elementu na górze stosu */
    public String top() throws Exception  {
        if(isEmpty())
            throw new Exception("Stack is empty, nothing to calculate - SYNTAX ERROR!");
        return stackTop.value;
    }

    /* Usuwa górny element ze stosu */
    public String pop() throws Exception  {
        if(isEmpty())
            throw new Exception("Stack is empty, nothing to calculate - SYNTAX ERROR!");
        String readed = stackTop.value;
        stackTop = stackTop.prev;
        return readed;
    }
}
