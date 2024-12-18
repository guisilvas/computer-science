// Guia_0707 - GATES
// Nome: Guilherme Soares Silva
// Matricula: 863485
module LU (
    output final_out,
    input [2:0] a,
    input [2:0] b,
    input select
);
    wire a_gt_b, a_lt_b;

    wire a0_gt_b0, a1_gt_b1, a2_gt_b2;
    wire a0_lt_b0, a1_lt_b1, a2_lt_b2;

    assign a0_gt_b0 = a[0] & ~b[0];
    assign a1_gt_b1 = a[1] & ~b[1];
    assign a2_gt_b2 = a[2] & ~b[2];

    assign a0_lt_b0 = ~a[0] & b[0];
    assign a1_lt_b1 = ~a[1] & b[1];
    assign a2_lt_b2 = ~a[2] & b[2];

    wire a_gt_b_bit0, a_gt_b_bit1, a_gt_b_bit2;
    assign a_gt_b_bit0 = a[0] & ~b[0];
    assign a_gt_b_bit1 = (a[1] & ~b[1]) | (a[1] & b[1] & a[0] & ~b[0]);
    assign a_gt_b_bit2 = (a[2] & ~b[2]) | (a[2] & b[2] & a[1] & ~b[1]) | (a[2] & b[2] & a[1] & b[1] & a[0] & ~b[0]);

    wire a_lt_b_bit0, a_lt_b_bit1, a_lt_b_bit2;
    assign a_lt_b_bit0 = ~a[0] & b[0];
    assign a_lt_b_bit1 = (~a[1] & b[1]) | (~a[1] & ~b[1] & a[0] & b[0]);
    assign a_lt_b_bit2 = (~a[2] & b[2]) | (~a[2] & ~b[2] & a[1] & b[1]) | (~a[2] & ~b[2] & a[1] & b[1] & a[0] & b[0]);

    assign a_gt_b = a_gt_b_bit0 | a_gt_b_bit1 | a_gt_b_bit2;
    assign a_lt_b = a_lt_b_bit0 | a_lt_b_bit1 | a_lt_b_bit2;

    wire not_select;
    not NOT1 (not_select, select);

    and AND1 (final_out, a_lt_b, not_select);
    and AND2 (final_out, a_gt_b, select);

    or OR1 (final_out, final_out, final_out);

endmodule

module test_LU;
    reg [2:0] x, y;
    reg s;
    wire z;

    LU modulo (
        .final_out(z),
        .a(x),
        .b(y),
        .select(s)
    );

    initial begin : main
        $display("Guia_0707 - Guilherme Soares Silva - 863485");
        x = 3'b000; y = 3'b000; s = 1'b0;

        #1 $monitor("%3b %3b %b %b", x, y, s, z);

        #1 s = 1'b1;
        x = 3'b001; y = 3'b010; s = 1'b0;
        #1 x = 3'b011; y = 3'b010; s = 1'b1;
        #1 x = 3'b101; y = 3'b110; s = 1'b0;
        #1 x = 3'b111; y = 3'b000; s = 1'b1;
        #1 $finish;
    end
endmodule

