package Application.Controller;

//
//还是一个数组实现3个栈，这次是弹性分割，通过设计数组成环状
/**
 * 思路：当一个栈的元素个数超出其初始容量时，就将这个栈扩容至许可的容量，必要时还要搬移元素。
 *          将数组设计成环状，最后一个栈可能从数组末尾开始，环绕到数组开头。
 */
class StackData{
    int size=0;
    int start;
    int capacity=0;
    int pointer=0;

    public StackData( int start, int capacity){
        this. start= start;
        this. capacity= capacity;
    }

    public boolean isWithinStack( int index, int totalSize){
        if( start<= index&& index< start+ capacity)
            return true;
        else if( start+ capacity> totalSize&& index<( start+ capacity)% totalSize)
            return true;
        return false;
    }
}

class SolutionB{
    static int numOfStack=3;
    static int defaultSize=4;
    static int totalSize=numOfStack*defaultSize;
    static StackData[] stacks={ new StackData(0, defaultSize), new StackData(defaultSize, defaultSize ),
            new StackData( defaultSize*2, defaultSize)};
    static int[] buffer=new int[totalSize];

    public static int numberOfElements(){
        return stacks[0]. size+ stacks[1]. size+ stacks[2]. size;
    }

    public static int nextElements( int index){
        if( index+1>= totalSize)
            return 0;
        else
            return index+1;
    }

    public static int previousElements( int index){
        if( index==0)
            return totalSize-1;
        else
            return index-1;
    }

    //以相反顺序搬移元素
    public static void shift(int stackNum){
        StackData stack= stacks[ stackNum];
        if( stack. size>= stack. capacity){
            int nextStack=( stackNum+1)% numOfStack;
            shift(nextStack);
            stack. capacity++;
        }

        for( int i=( stack. size+ stack. capacity-1)% totalSize; stack.isWithinStack( i, totalSize);
             previousElements(i)){
            buffer[ i]= buffer[ previousElements(i)];
        }
        stack. start=0;
        stack. start= nextElements(stack.start);
        stack. pointer= nextElements(stack.start);
        stack. capacity--;
    }

    /*搬移到其他栈上，以扩大容量*/
    public static void expand(int stackNum){
        shift((stackNum+1)%totalSize);
        stacks[ stackNum]. capacity++;
    }

    public static void push(int stackNum,int value) throws Exception{
        StackData stack= stacks[ stackNum];
        //检查空间是否足够
        if( stack. size>= stack. capacity){
            if( numberOfElements()>=totalSize)
                throw new Exception( "Out fo space.");
            else
                expand(stackNum);
        }

        stack. size++;
        stack. pointer= nextElements(stack.pointer);
        buffer[ stack. pointer]= value;
    }

    public static int pop(int stackNum) throws Exception{
        StackData stack= stacks[ stackNum];
        if( stack. size==0)
            throw new Exception( "Tryint to pop an empty stack.");

        int value= buffer[ stack. pointer];
        buffer[ stack. pointer]=0;
        stack. pointer= previousElements(stack.pointer);
        stack. size--;

        return value;
    }

    public static int peek(int stackNum) throws Exception{
        StackData stack= stacks[ stackNum];
        return buffer[ stack. pointer];
    }

    public static boolean isEmpty( int stackNum){
        StackData stack= stacks[ stackNum];
        return stack. size==0;
    }
}
