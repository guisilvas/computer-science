// Guia_0701 - GATES
// Nome: Guilherme Soares Silva
// Matricula: 863485
module LU (output and_out, output nand_out, input a, input b);
    and AND1 (and_out, a, b);
    nand NAND1 (nand_out, a, b);
endmodule

module mux (output s, input a, input b, input select);
    wire not_select;
    wire sa;
    wire sb;
    
    not NOT1 (not_select, select);
    and AND1 (sa, a, not_select);
    and AND2 (sb, b, select);
    or OR1 (s, sa, sb);
endmodule

module test_LU;
reg x;
reg y;
reg s;
wire and_out;
wire nand_out;
wire z;

LU modulo (and_out, nand_out, x, y);
mux MUX1 (z, nand_out, and_out, s);

initial begin : main
    $display("Guia_0701 - Guilherme Soares Silva - 863485");
    x = 1'b0; y = 1'b1; s = 1'b0;
    
    #1 $monitor("%4b %4b %4b | %4b  %4b  | %4b", x, y, s, and_out, nand_out, z);
    
    #1 x = 1'b0; y = 1'b0; s = 1'b0;
    #1 x = 1'b1; y = 1'b0; s = 1'b0;
    #1 x = 1'b1; y = 1'b1; s = 1'b1;
    #1 s = 1'b1;
end

endmodule

