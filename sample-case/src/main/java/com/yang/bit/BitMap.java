package com.yang.bit;

public class BitMap {
    /** 插入数的最大长度，比如100，那么允许插入bitsMap中的最大数为99 */
    private long length;
    private static int[] bitsMap;
    private static final int[] BIT_VALUE = { 0x00000001, 0x00000002, 0x00000004, 0x00000008, 0x00000010, 0x00000020,
            0x00000040, 0x00000080, 0x00000100, 0x00000200, 0x00000400, 0x00000800, 0x00001000, 0x00002000, 0x00004000,
            0x00008000, 0x00010000, 0x00020000, 0x00040000, 0x00080000, 0x00100000, 0x00200000, 0x00400000, 0x00800000,
            0x01000000, 0x02000000, 0x04000000, 0x08000000, 0x10000000, 0x20000000, 0x40000000, 0x80000000 };
    public BitMap(long length) {
        this.length = length;
        // 根据长度算出，所需数组大小
        bitsMap = new int[(int) (length >> 5) + ((length & 31) > 0 ? 1 : 0)];
    }
    /**
     * 根据长度获取数据 比如输入63，那么实际上是确定数62是否在bitsMap中
     * 
     * @return index 数的长度
     * @return 1:代表数在其中 0:代表
     */
    public int getBit(long index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("length value illegal!");
        }
        int intData = (int) bitsMap[(int) ((index - 1) >> 5)];
        return ((intData & BIT_VALUE[(int) ((index - 1) & 31)])) >>> ((index - 1) & 31);
    }
    /**
     * @param index
     * 要被设置的值为index - 1
     */
    public void setBit(long index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("length value illegal!");
        }
        // 求出该index - 1所在bitMap的下标
        int belowIndex = (int) ((index - 1) >> 5);
        // 求出该值的偏移量(求余)
        int offset = (int) ((index - 1) & 31);
        int inData = bitsMap[belowIndex];
        bitsMap[belowIndex] = inData | BIT_VALUE[offset];
    }
    
    public static void main(String[] args) {
        BitMap bitMap = new BitMap(63);
        bitMap.setBit(63);
        System.out.println(bitMap.getBit(63));
        System.out.println(bitMap.getBit(62));
    }
    
}

