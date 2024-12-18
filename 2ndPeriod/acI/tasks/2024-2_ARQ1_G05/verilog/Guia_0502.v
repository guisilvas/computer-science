// -------------------------
// Guia_0502.v
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

module Guia_0502 (output s, input a, input b);
  wire not_b, nand1, nand2;

  nand NAND1 (not_b, b, b);   
 
  nand NAND2 (nand1, a, not_b); 

  nand NAND3 (s, nand1, nand1);
  
endmodule


module test_Guia_0502;
  reg a, b;  
  wire s;    

  Guia_0502 M1 (s, a, b);

  initial begin
    
    $display("Guia_0502 Test");
    $display("a | ~b = s\n");

    a = 0; b = 0; #1 $display("%b | ~%b = %b", a, b, s);
    a = 0; b = 1; #1 $display("%b | ~%b = %b", a, b, s);
    a = 1; b = 0; #1 $display("%b | ~%b = %b", a, b, s);
    a = 1; b = 1; #1 $display("%b | ~%b = %b", a, b, s);
  end
endmodule


