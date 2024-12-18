// -------------------------
// Guia_0804 - INEQUALITY COMPARATOR
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

// Comparador de desigualdade para 1 bit
// -------------------------
module bitComparator (output s,  
                      input a,   
                      input b);  
  xor XOR1 (s, a, b);  
endmodule 

// -------------------------
// Comparador de desigualdade para 5 bits
// -------------------------
module inequalityComparator (output not_equal,  
                             input [4:0] a,    
                             input [4:0] b);   
  wire [4:0] s;  

  // Comparar cada bit
  bitComparator BC0 (s[0], a[0], b[0]);
  bitComparator BC1 (s[1], a[1], b[1]);
  bitComparator BC2 (s[2], a[2], b[2]);
  bitComparator BC3 (s[3], a[3], b[3]);
  bitComparator BC4 (s[4], a[4], b[4]);

  // Verificar se pelo menos um bit é diferente
  or OR1 (not_equal, s[0], s[1], s[2], s[3], s[4]);
endmodule // inequalityComparator

// -------------------------
// Testbench
// -------------------------
module test_inequalityComparator;
  reg [4:0] x;
  reg [4:0] y;
  wire not_equal;
  
  // Instanciar o comparador de desigualdade
  inequalityComparator ineq_instance (.not_equal(not_equal), .a(x), .b(y));
  
  initial begin
    $display("Guia_0804 - Guilherme Soares Silva - 863485");
    $display("Test LU’s inequality comparator");
    
    // Testes
    x = 5'b10101; y = 5'b10101; 
    #1 $display("x = %b, y = %b, not_equal = %b", x, y, not_equal);
    
    x = 5'b11111; y = 5'b00001; 
    #1 $display("x = %b, y = %b, not_equal = %b", x, y, not_equal);
    
    x = 5'b01110; y = 5'b01110; 
    #1 $display("x = %b, y = %b, not_equal = %b", x, y, not_equal);
    
    x = 5'b11100; y = 5'b11101; 
    #1 $display("x = %b, y = %b, not_equal = %b", x, y, not_equal);
  end
endmodule // test_inequalityComparator

