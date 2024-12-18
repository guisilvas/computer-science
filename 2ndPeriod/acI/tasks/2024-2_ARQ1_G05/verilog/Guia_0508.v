// -------------------------
// Guia_0508.v
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

`timescale 1ns / 1ps

module Guia_0508 (
    input a,
    input b,
    output y
);

    wire na;
    wire nb;
    wire na_nand_nb;
    
    assign na = ~(a & a);
    assign nb = ~(b & b);
    assign na_nand_nb = ~(na & nb);
    assign y = ~(na_nand_nb & na_nand_nb);

endmodule

module Guia_0508_tb;

    reg a;
    reg b;
    wire y;
    
    Guia_0508 uut (
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

