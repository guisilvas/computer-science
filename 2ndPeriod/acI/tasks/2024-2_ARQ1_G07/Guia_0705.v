// Guia_0701 - GATES
// Nome: Guilherme Soares Silva
// Matricula: 863485
module LU (output final_out, input a, input b, input [2:0] select, input negate);
    wire not_a, and_out, nand_out, or_out, nor_out, xor_out, xnor_out;
    wire sa, sb, sc, sd, se, sf, sg;

    not NOT_OP (not_a, a);
    wire a_input = negate ? not_a : a;

    not NOT1 (sa, a_input);
    and AND1 (and_out, a_input, b);
    nand NAND1 (nand_out, a_input, b);
    or OR1 (or_out, a_input, b);
    nor NOR1 (nor_out, a_input, b);
    xor XOR1 (xor_out, a_input, b);
    xnor XNOR1 (xnor_out, a_input, b);

    wire not_select0, not_select1, not_select2;
    wire sa1, sa2, sa3, sa4, sa5, sa6, sa7;

    not NOT2 (not_select0, select[0]);
    not NOT3 (not_select1, select[1]);
    not NOT4 (not_select2, select[2]);

    and AND2 (sa1, sa, not_select2, not_select1, not_select0);
    and AND3 (sa2, and_out, not_select2, not_select1, select[0]);
    and AND4 (sa3, nand_out, not_select2, select[1], not_select0);
    and AND5 (sa4, or_out, not_select2, select[1], select[0]);
    and AND6 (sa5, nor_out, select[2], not_select1, not_select0);
    and AND7 (sa6, xor_out, select[2], not_select1, select[0]);
    and AND8 (sa7, xnor_out, select[2], select[1], not_select0);

    or OR2 (final_out, sa1, sa2, sa3, sa4, sa5, sa6, sa7);

endmodule

module test_LU;
reg x;
reg y;
reg [2:0] select;
reg negate;
wire z;

LU modulo (z, x, y, select, negate);

initial begin : main
    $display("Guia_0705 - Guilherme Soares Silva - 863485");
    x = 1'b0; y = 1'b1; negate = 1'b0; select = 3'b000;

    #1 $monitor("%4b %4b %4b %3b | %4b", x, y, negate, select, z);

    #1 select = 3'b000; negate = 1'b1;
    #1 select = 3'b001; negate = 1'b0;
    #1 select = 3'b010; negate = 1'b0;
    #1 select = 3'b011; negate = 1'b0;
    #1 select = 3'b100; negate = 1'b0;
    #1 select = 3'b101; negate = 1'b0;
    #1 select = 3'b110; negate = 1'b0;
end

endmodule

