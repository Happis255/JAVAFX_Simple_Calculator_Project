package calculator.java_files;

/* Stos - reprezentacja stosu wykorzystywanego do wykonywania obliczeń ONP */
class Stack {

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
    Stack() {
        stackTop = null;
    }

    /* Sprawdza, czy na stosie jest odłożony element */
    boolean isEmpty() {
        return stackTop == null;
    }

    /* Dodaje element na stos */
    void push(String str) {

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
    String top() throws Exception  {
        if(isEmpty())
            throw new Exception("Stack is empty, nothing to calculate - SYNTAX ERROR!");
        return stackTop.value;
    }

    /* Usuwa górny element ze stosu */
    String pop() throws Exception  {
        if(isEmpty())
            throw new Exception("Stack is empty, nothing to calculate - SYNTAX ERROR!");
        String readed = stackTop.value;
        stackTop = stackTop.prev;
        return readed;
    }
}
