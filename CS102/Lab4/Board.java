public class Board
{
    Slot slotA = new Slot(4, "A");
    Slot slotB = new Slot(4, "B");
    Slot slotC = new Slot(4, "C");
    Slot slotD = new Slot(5, "D");
    Slot slotE = new Slot(5, "E");
    Slot slotF = new Slot(5, "F");
    Slot slotG = new Slot(6, "G");
    Slot slotH = new Slot(6, "H");
    Slot slotI = new Slot(6, "I");
    Slot slotJ = new Slot(7, "J");
    Slot slotK = new Slot(7, "K");
    Slot slotL = new Slot(7, "L");
    Slot slot0 = new Slot(0, "0");
    Slot slot1 = new Slot(1, "1");
    Slot slot2 = new Slot(2, "2");
    Slot slot3 = new Slot(3, "3");

    public Slot getSlot(int index)
    {
        switch (index)
        {
        case 0:
            // Special case
            return slot0;
        case 1:
            return slotA;
        case 2:
            return slotB;
        case 3:
            return slotC;
        case 4:
            // Special case
            return slot1;
        case 5:
            return slotD;
        case 6:
            return slotE;
        case 7:
            return slotF;
        case 8:
            // Special case
            return slot2;
        case 9:
            return slotG;
        case 10:
            return slotH;
        case 11:
            return slotI;
        case 12:
            // Special case
            return slot3;
        case 13:
            return slotJ;
        case 14:
            return slotK;
        case 15:
            return slotL;
        default:
            return null;
        }
    }

}
