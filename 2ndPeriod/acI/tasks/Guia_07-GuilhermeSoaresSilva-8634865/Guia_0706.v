// Guia_0706 - GATES
// Nome: Guilherme Soares Silva
// Matricula: 863485
module LU (
    output final_out,
    input [2:0] a,
    input [2:0] b,
    input select
);
    wire eq0, eq1, eq2;
    wire neq0, neq1, neq2;

    assign eq0 = a[0] ~^ b[0];
    assign eq1 = a[1] ~^ b[1];
    assign eq2 = a[2] ~^ b[2];
    
    assign neq0 = a[0] ^ b[0];
    assign neq1 = a[1] ^ b[1];
    assign neq2 = a[2] ^ b[2];

    wire eq_all, neq_all;
    
    and AND1 (eq_all, eq0, eq1, eq2);
    or OR1 (neq_all, neq0, neq1, neq2);
    
    wire not_select;
    not NOT1 (not_select, select);

    and AND2 (final_out, eq_all, not_select);
    and AND3 (final_out, neq_all, select);
    
    or OR2 (final_out, final_out, final_out);

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
        $display("Guia_0706 - Guilherme Soares Silva - 863485");
        x = 3'b000; y = 3'b000; s = 1'b0;

        #1 $monitor("%3b %3b %b %b", x, y, s, z);

        #1 s = 1'b1;
        x = 3'b101; y = 3'b101; s = 1'b0;
        #1 x = 3'b110; y = 3'b101; s = 1'b1;
        #1 x = 3'b111; y = 3'b000; s = 1'b1;
        #1 $finish;
    end
endmodule

