// -------------------------
// Guia_0506.v
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

`timescale 1ns / 1ps

module Guia_0506 (
    input a,
    input b,
    output y
);

    wire na;
    wire nb;
    wire nand_ab;
    wire nand_na_nb;
    
    assign na = ~(a & a);
    assign nb = ~(b & b);
    assign nand_ab = ~(a & b);
    assign nand_na_nb = ~(na & nb);
    assign y = ~(nand_ab & nand_na_nb);

endmodule

module Guia_0506_tb;

    reg a;
    reg b;
    wire y;
    
    Guia_0506 uut (
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

