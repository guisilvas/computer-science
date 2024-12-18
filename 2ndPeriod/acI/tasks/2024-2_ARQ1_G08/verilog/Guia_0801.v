// -------------------------
// Guia_0801 - FULL ADDER
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

// -------------------------
// half adder
// -------------------------
module halfAdder (output s1,  
                  output s0,  
                  input a,    
                  input b);   
  xor XOR1 (s0, a, b);  
  and AND1 (s1, a, b);  
endmodule 

// -------------------------
// full adder
// -------------------------
module fullAdder (output s1,   
                  output s0,   
                  input a,     
                  input b,     
                  input carryIn); 
  wire w1, w2, w3;
  
  // Meia soma dos bits de entrada
  halfAdder HA1 (w1, w2, a, b);
  
  // Meia soma do resultado anterior com o carry in
  halfAdder HA2 (s1, w3, w2, carryIn);
  
  // Soma dos carries
  or OR1 (s0, w1, w3);
endmodule 

// -------------------------
// AU de 5 bits
// -------------------------
module AU (output [4:0] soma,   
           input [4:0] a,       
           input [4:0] b);      
  wire [4:0] carry;             

  // Somadores completos para cada bit
  fullAdder FA0 (carry[0], soma[0], a[0], b[0], 1'b0);
  fullAdder FA1 (carry[1], soma[1], a[1], b[1], carry[0]);
  fullAdder FA2 (carry[2], soma[2], a[2], b[2], carry[1]);
  fullAdder FA3 (carry[3], soma[3], a[3], b[3], carry[2]);
  fullAdder FA4 (carry[4], soma[4], a[4], b[4], carry[3]);
endmodule // AU

// -------------------------
// Testbench
// -------------------------
module test_AU;
  reg [4:0] x;
  reg [4:0] y;
  wire [4:0] soma;
  
  // AU
  AU au_instance (.soma(soma), .a(x), .b(y));
  
  initial begin
    $display("Guia_0801 - Guilherme Soares Silva - 863485");
    $display("Test AU’s full adder");
    
    // Tests
    x = 5'b10101; y = 5'b01010;
    #1 $display("x = %b, y = %b, soma = %b", x, y, soma);
    
    x = 5'b11111; y = 5'b00001;
    #1 $display("x = %b, y = %b, soma = %b", x, y, soma);
    
    x = 5'b01110; y = 5'b00101;
    #1 $display("x = %b, y = %b, soma = %b", x, y, soma);
  end
endmodule // test_AU

