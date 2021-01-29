package org.example.nes;

import java.util.Arrays;

public class Bus {
    private CPU6502 cpu;
    private int[] ram = new int[64 * 1024];

    public Bus() {
        Arrays.fill(ram, 0x00);

        cpu.connectBus(this);
    }

    // write data to ram on address as 8bits
    public void write(int address, int data) {
        if (address >= 0x0000 && address <= 0xFFFF)
            ram[address] = data & 0xFF;
    }

    // returns data from ram as 8bits
    public int read(int address, boolean bReadOnly) {
        if (address >= 0x0000 && address <= 0xFFFF)
            return ram[address] & 0xFF;
        return 0x00;
    }
}
