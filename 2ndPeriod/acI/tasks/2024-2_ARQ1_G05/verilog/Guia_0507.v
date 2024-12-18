// -------------------------
// Guia_0507.v
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

`timescale 1ns / 1ps

module Guia_0507 (
    input a,
    input b,
    output y
);

    wire na;
    wire nb;
    wire na_nor_nb;
    wire a_nor_b;
    
    assign na = ~(a | a);
    assign nb = ~(b | b);
    assign na_nor_nb = ~(na | nb);
    assign a_nor_b = ~(a | b);
    assign y = ~(na_nor_nb | a_nor_b);

endmodule

module Guia_0507_tb;

    reg a;
    reg b;
    wire y;
    
    Guia_0507 uut (
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


