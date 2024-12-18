// -------------------------
// Guia_0505.v
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

`timescale 1ns / 1ps

module Guia_0505 (
    input a,
    input b,
    output y
);

    wire na;
    wire nb;
    wire a_nor_b;
    wire not_a_nor_not_b;
    
    assign na = ~(a | a);
    assign nb = ~(b | b);
    assign a_nor_b = ~(a | b);
    assign not_a_nor_not_b = ~(na | nb);
    assign y = ~(a_nor_b | not_a_nor_not_b);

endmodule

module Guia_0505_tb;

    reg a;
    reg b;
    wire y;
    
    Guia_0505 uut (
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

