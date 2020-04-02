package calculator.java_files;

public class Stack {

    private static class StackItem{
        public String data;
        public StackItem prev;
    }

    private StackItem stackTop;

    public Stack()
    {
        stackTop = null;
    }

    public boolean isEmpty()
    {
        return stackTop == null;
    }

    public void push(String str)
    {
        StackItem newItem = new StackItem();
        newItem.data = str;

        if(isEmpty()){
            stackTop = newItem;
            stackTop.prev = null;
        } else {
            newItem.prev = stackTop;
            stackTop = newItem;
        }
    }

    public String top() throws Exception
    {
        if(stackTop == null)
            throw new Exception("Stack is empty!");

        return stackTop.data;
    }

    public String pop() throws Exception
    {
        if(stackTop == null)
            throw new Exception("Stack is empty!");

        String retData = stackTop.data;
        stackTop = stackTop.prev;
        return retData;
    }
}
