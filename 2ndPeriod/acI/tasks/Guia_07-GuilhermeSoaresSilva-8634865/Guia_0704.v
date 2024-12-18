// Guia_0701 - GATES
// Nome: Guilherme Soares Silva
// Matricula: 863485
module LU (output final_out, input a, input b, input [1:0] select);
    wire or_out, nor_out, xor_out, xnor_out;
    wire not_select0, not_select1;
    wire sa, sb, sc, sd;

    or OR1 (or_out, a, b);
    nor NOR1 (nor_out, a, b);
    xor XOR1 (xor_out, a, b);
    xnor XNOR1 (xnor_out, a, b);

    not NOT1 (not_select0, select[0]);
    not NOT2 (not_select1, select[1]);

    and AND1 (sa, nor_out, not_select1, not_select0);
    and AND2 (sb, or_out, not_select1, select[0]);
    and AND3 (sc, xor_out, select[1], not_select0);
    and AND4 (sd, xnor_out, select[1], select[0]);

    or OR2 (final_out, sa, sb, sc, sd);
endmodule

module test_LU;
reg x;
reg y;
reg [1:0] select;
wire z;

LU modulo (z, x, y, select);

initial begin : main
    $display("Guia_0701 - Guilherme Soares Silva - 863485");
    x = 1'b0; y = 1'b1; select = 2'b00;

    #1 $monitor("%4b %4b %2b | %4b", x, y, select, z);

    #1 x = 1'b0; y = 1'b0; select = 2'b00;
    #1 x = 1'b1; y = 1'b0; select = 2'b01;
    #1 x = 1'b1; y = 1'b1; select = 2'b10;
    #1 x = 1'b1; y = 1'b1; select = 2'b11;
end

endmodule

