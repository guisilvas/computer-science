// -------------------------
// Guia_0806 - ALGEBRAIC ADDER WITH EQUALITY & INEQUALITY CHECK
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

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
// C1 soma/subtração
// -------------------------
module conditionalInverter (output [5:0] b_inverted,  
                            input [5:0] b,            
                            input subtract);          
  assign b_inverted = subtract ? ~b : b;  
endmodule // conditionalInverter

// -------------------------
// Somador/Subtrator de 6 bits com verificação de igualdade e desigualdade
// -------------------------
module algebraicAdder (output [5:0] result,      
                       output equal,            
                       output not_equal,        
                       input [5:0] a,           
                       input [5:0] b,           
                       input carryIn,           
                       input select);           
                       
  wire [5:0] b_inverted;
  wire [5:0] sum;
  wire [5:0] carry;
  wire [5:0] eq_bits;
  
  // Inverter condicionalmente o operando B para subtração
  conditionalInverter CI (b_inverted, b, carryIn);
  
  // Realizar soma/subtração usando somadores completos
  fullAdder FA0 (carry[0], sum[0], a[0], b_inverted[0], carryIn);
  fullAdder FA1 (carry[1], sum[1], a[1], b_inverted[1], carry[0]);
  fullAdder FA2 (carry[2], sum[2], a[2], b_inverted[2], carry[1]);
  fullAdder FA3 (carry[3], sum[3], a[3], b_inverted[3], carry[2]);
  fullAdder FA4 (carry[4], sum[4], a[4], b_inverted[4], carry[3]);
  fullAdder FA5 (carry[5], sum[5], a[5], b_inverted[5], carry[4]);

  // Verify by XNOR
  xnor (eq_bits[0], a[0], b[0]);
  xnor (eq_bits[1], a[1], b[1]);
  xnor (eq_bits[2], a[2], b[2]);
  xnor (eq_bits[3], a[3], b[3]);
  xnor (eq_bits[4], a[4], b[4]);
  xnor (eq_bits[5], a[5], b[5]);

  // bit equals
  and (equal, eq_bits[0], eq_bits[1], eq_bits[2], eq_bits[3], eq_bits[4], eq_bits[5]);

  // bit difference
  nor (not_equal, equal);  

  select
  assign result = select ? not_equal : equal;
endmodule // algebraicAdder

// -------------------------
// Testbench
// -------------------------
module test_algebraicAdder;
  reg [5:0] x;
  reg [5:0] y;
  reg carryIn;
  reg select;
  wire [5:0] result;
  wire equal;
  wire not_equal;

  // Instanciar o somador algébrico
  algebraicAdder aa_instance (.result(result), .equal(equal), .not_equal(not_equal), .a(x), .b(y), .carryIn(carryIn), .select(select));

  initial begin
    $display("Guia_0806 - Guia_0805 - Guilherme Soares Silva - 863485");
    $display("Test AU’s algebraic adder with equality and inequality");
    
    // Testes
    x = 6'b101001; y = 6'b101001; carryIn = 0; select = 0;  
    #1 $display("x = %b, y = %b, carryIn = %b, result = %b, equal = %b, not_equal = %b", x, y, carryIn, result, equal, not_equal);
    
    x = 6'b111111; y = 6'b000001; carryIn = 1; select = 1;  
    #1 $display("x = %b, y = %b, carryIn = %b, result = %b, equal = %b, not_equal = %b", x, y, carryIn, result, equal, not_equal);

    x = 6'b010101; y = 6'b010101; carryIn = 0; select = 0;  
    #1 $display("x = %b, y = %b, carryIn = %b, result = %b, equal = %b, not_equal = %b", x, y, carryIn, result, equal, not_equal);
  end
endmodule // test_algebraicAdder

