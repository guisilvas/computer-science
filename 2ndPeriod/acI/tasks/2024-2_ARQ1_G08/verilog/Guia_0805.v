// -------------------------
// Guia_0805 - 2's COMPLEMENT CALCULATOR
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

// -------------------------
// C1
// -------------------------
module oneComplement (output [5:0] complement1,  
                      input [5:0] a);            
  not NOT0 (complement1[0], a[0]);
  not NOT1 (complement1[1], a[1]);
  not NOT2 (complement1[2], a[2]);
  not NOT3 (complement1[3], a[3]);
  not NOT4 (complement1[4], a[4]);
  not NOT5 (complement1[5], a[5]);
endmodule // oneComplement

// -------------------------
// 1 bit full adder
// -------------------------
module fullAdder (output carryOut,  
                  output sum,       
                  input a,          
                  input b,          
                  input carryIn);   
  wire w1, w2, w3;
  xor XOR1 (w1, a, b);
  xor XOR2 (sum, w1, carryIn);
  and AND1 (w2, a, b);
  and AND2 (w3, w1, carryIn);
  or OR1 (carryOut, w2, w3);
endmodule // fullAdder

// -------------------------
// 6 bits C2
// -------------------------
module twoComplement (output [5:0] complement2,  
                      input [5:0] a);            
  wire [5:0] complement1;  
  wire [5:0] carry;        

  // C1
  oneComplement OC1 (complement1, a);

  // C1 + 1
  fullAdder FA0 (carry[0], complement2[0], complement1[0], 1'b0, 1'b1);
  fullAdder FA1 (carry[1], complement2[1], complement1[1], 1'b0, carry[0]);
  fullAdder FA2 (carry[2], complement2[2], complement1[2], 1'b0, carry[1]);
  fullAdder FA3 (carry[3], complement2[3], complement1[3], 1'b0, carry[2]);
  fullAdder FA4 (carry[4], complement2[4], complement1[4], 1'b0, carry[3]);
  fullAdder FA5 (carry[5], complement2[5], complement1[5], 1'b0, carry[4]);
endmodule // twoComplement

// -------------------------
// Testbench
// -------------------------
module test_twoComplement;
  reg [5:0] x;
  wire [5:0] complement2;

  // C2
  twoComplement tc_instance (.complement2(complement2), .a(x));

  initial begin
    $display("Guia_0805 - Guilherme Soares Silva - 863485");
    $display("Test LU’s 2's complement calculator");
    
    // Testes
    x = 6'b000001; 
    #1 $display("x = %b, 2's complement = %b", x, complement2);
    
    x = 6'b101010; 
    #1 $display("x = %b, 2's complement = %b", x, complement2);
    
    x = 6'b111111; 
    #1 $display("x = %b, 2's complement = %b", x, complement2);
  end
endmodule // test_twoComplement

