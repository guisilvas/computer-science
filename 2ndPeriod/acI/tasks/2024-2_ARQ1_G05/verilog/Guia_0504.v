// -------------------------
// Guia_0504.v
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

`timescale 1ns / 1ps

module Guia_0504 (
    input a,
    input b,
    output y
);

    wire na;
    wire nand_ab;
    
    assign na = ~(a & a); 

    assign nand_ab = ~(na & b); 
    
    assign y = ~(nand_ab); 

endmodule

module Guia_0504_tb;

    reg a;
    reg b;
    wire y;
    
    Guia_0504 uut (
        .a(a),
        .b(b),
        .y(y)
    );
    
    initial begin
        a = 0; b = 0;
        #10;

        a = 0; b = 1;
        #10;

        a = 1; b = 0;
        #10;

        a = 1; b = 1;
        #10;

        $finish;
    end

    initial begin
        $monitor("Tempo = %0t: a = %b, b = %b, y = %b", $time, a, b, y);
    end

endmodule

