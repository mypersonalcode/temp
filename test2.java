public class test2 {
    //这是一个test题，关于如何通过一个数组实现3个栈，我这边开发的固定大小的分为3个栈。
    int stackSize = 100;
    int[] buffer = new int[3 * stackSize];
    int[] stackPointer = {-1, -1, -1};

    public void push(int stackNum, int value) throws Exception {
        if (stackPointer[stackNum] + 1 >= stackSize)
            throw new Exception("Out of space.");
        stackPointer[stackNum]++;
        buffer[absTopOfStack(stackNum)] = value;
    }

    public int pop(int stackNum) throws Exception {
        if (stackPointer[stackNum] == -1)
            throw new Exception("Trying to pop an empty stack.");
        int value = buffer[absTopOfStack(stackNum)];
        buffer[absTopOfStack(stackNum)] = 0;
        stackPointer[stackNum]--;
        return value;
    }

    public int peek(int stackNum) {
        return buffer[absTopOfStack(stackNum)];
    }

    public boolean isEmpty(int stackNum) {
        return stackPointer[stackNum] == -1;
    }

    public int absTopOfStack(int stackNum) {
        return stackNum * stackSize + stackPointer[stackNum];
    }
}
