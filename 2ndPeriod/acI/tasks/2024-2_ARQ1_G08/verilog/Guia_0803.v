// -------------------------
// Guia_0803 - EQUALITY COMPARATOR
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

// -------------------------
// Comparador de igualdade para 1 bit
// -------------------------
module bitComparator (output s,  
                      input a,   
                      input b);  
  xnor XNOR1 (s, a, b);  
endmodule 

// -------------------------
// Comparador de igualdade para 5 bits
// -------------------------
module equalityComparator (output equal,  
                           input [4:0] a,  
                           input [4:0] b); 
  wire [4:0] s;  

  // Comparar cada bit
  bitComparator BC0 (s[0], a[0], b[0]);
  bitComparator BC1 (s[1], a[1], b[1]);
  bitComparator BC2 (s[2], a[2], b[2]);
  bitComparator BC3 (s[3], a[3], b[3]);
  bitComparator BC4 (s[4], a[4], b[4]);

  // Verificar se todos os bits são iguais
  and AND1 (equal, s[0], s[1], s[2], s[3], s[4]);
endmodule // equalityComparator

// -------------------------
// Testbench
// -------------------------
module test_equalityComparator;
  reg [4:0] x;
  reg [4:0] y;
  wire equal;
  
  // equality comparator
  equalityComparator eq_instance (.equal(equal), .a(x), .b(y));
  
  initial begin
    $display("Guia_0803 - Guilherme Soares Silva - 863485");
    $display("Test LU’s equality comparator");
    
    // Testes
    x = 5'b10101; y = 5'b10101; 
    #1 $display("x = %b, y = %b, equal = %b", x, y, equal);
    
    x = 5'b11111; y = 5'b00001; 
    #1 $display("x = %b, y = %b, equal = %b", x, y, equal);
    
    x = 5'b01110; y = 5'b01110; 
    #1 $display("x = %b, y = %b, equal = %b", x, y, equal);
    
    x = 5'b11100; y = 5'b11101; 
    #1 $display("x = %b, y = %b, equal = %b", x, y, equal);
  end
endmodule // test_equalityComparator

