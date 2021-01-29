package org.example.nes;

public class CPU6502 {
    // 8bits accumulator
    private int A;

    // 8bits registers
    private int X;
    private int Y;

    // 9bits stack pointer
    private int stkp;

    /* 8bits status bit flag
     * 0: CARRY 1=TRUE
     * 1: ZERO 1=RESULT ZERO
     * 2: IRQ DISABLE 1=DISABLE
     * 3: DECIMAL MODE 1=TRUE
     * 4: BRK COMMAND 1=BRK
     * 5: UNUSED 1 lol
     * 6: OVERFLOW 1=TRUE
     * 7: NEGATIVE 1=NEG
     */
    private int status;

    public enum FLAGS6502 {
        C(1 << 0), Z(1 << 1), I(1 << 2), D(1 << 3), B(1 << 4), U(1 << 5), O(1 << 6), N(1 << 7);

        int bit;
        FLAGS6502(int bit) {this.bit = bit;}
    }

    // program counter 16bits
    private int pc;

    // bus reference
    private Bus bus;

    public void connectBus(Bus bus) {
        this.bus = bus;
    }

    private int read(int address) {
        return bus.read(address, false);
    }

    private void write(int address, int data) {
        bus.write(address, data);
    }

    private int getFlag(FLAGS6502 f) {
        return status & f.bit;
    }

    private void setFlag(FLAGS6502 f, boolean v) {
        status = v ? status | f.bit : status & (f.bit ^ 0xFF);
    }
}
