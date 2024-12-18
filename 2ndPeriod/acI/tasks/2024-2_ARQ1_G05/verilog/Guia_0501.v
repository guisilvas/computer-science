// -------------------------
// Guia_0501.v
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

module guia_0501 (output s, input a, input b);
    wire nota;
    wire notb;
    wire nota_and_b;

    nor NOR1(nota, a, a);

    nor NOR2(notb, b, b);

    nor NOR3(temp1, nota, nota); 
    nor NOR4(temp2, notb, notb); 
    nor NOR5(nota_and_b, temp1, temp2);

    assign s = nota_and_b;
endmodule

module test_guia_0501;
    reg a, b;
    wire s;

    guia_0501 UUT (s, a, b);

    initial begin
        $display("Guia_0501 - (~a & b) using NOR");
        $display(" a | b || s ");
        $display("------------");
        a = 0; b = 0; #1 $display(" %b | %b || %b ", a, b, s);
        a = 0; b = 1; #1 $display(" %b | %b || %b ", a, b, s);
        a = 1; b = 0; #1 $display(" %b | %b || %b ", a, b, s);
        a = 1; b = 1; #1 $display(" %b | %b || %b ", a, b, s);
    end
endmodule

