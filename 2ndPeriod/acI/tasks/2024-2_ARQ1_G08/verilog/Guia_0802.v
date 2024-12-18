// -------------------------
// Guia_0802 - FULL ADDER
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

// -------------------------
// half subtractor
// -------------------------
module halfSubtractor (output d1,  
                       output d0,  
                       input a,    
                       input b);   
  xor XOR1 (d0, a, b); 
  and AND1 (w1, ~a, b);  
  or OR1 (d1, w1);      
endmodule // halfSubtractor

// -------------------------
// full subtractor
// -------------------------
module fullSubtractor (output d1,   
                       output d0,   
                       input a,     
                       input b,     
                       input borrowIn); 
  wire w1, w2, w3;
  
  // Meia diferença dos bits de entrada
  halfSubtractor HS1 (w1, w2, a, b);
  
  // Meia diferença com borrow in
  halfSubtractor HS2 (d1, w3, w2, borrowIn);
  
  // Soma dos borrows
  or OR1 (d0, w1, w3);
endmodule // fullSubtractor

// -------------------------
// AU de 5 bits para subtração
// -------------------------
module AU (output [4:0] diff,   
           input [4:0] a,       
           input [4:0] b);      
  wire [4:0] borrow;            

  // Subtratores completos para cada bit
  fullSubtractor FS0 (borrow[0], diff[0], a[0], b[0], 1'b0);
  fullSubtractor FS1 (borrow[1], diff[1], a[1], b[1], borrow[0]);
  fullSubtractor FS2 (borrow[2], diff[2], a[2], b[2], borrow[1]);
  fullSubtractor FS3 (borrow[3], diff[3], a[3], b[3], borrow[2]);
  fullSubtractor FS4 (borrow[4], diff[4], a[4], b[4], borrow[3]);
endmodule // AU

// -------------------------
// Testbench
// -------------------------
module test_AU;
  reg [4:0] x;
  reg [4:0] y;
  wire [4:0] diff;
  
  // AU
  AU au_instance (.diff(diff), .a(x), .b(y));
  
  initial begin
    $display("Guia_0802 - Guilherme Soares Silva - 863485");
    $display("Test AU’s full subtractor");
    
    // Testes
    x = 5'b10101; y = 5'b01010; 
    #1 $display("x = %b, y = %b, diff = %b", x, y, diff);
    
    x = 5'b11111; y = 5'b00001; 
    #1 $display("x = %b, y = %b, diff = %b", x, y, diff);
    
    x = 5'b01110; y = 5'b00101; 
    #1 $display("x = %b, y = %b, diff = %b", x, y, diff);
  end
endmodule // test_AU

