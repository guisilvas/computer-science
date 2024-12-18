// -------------------------
// Guia_0807 - COMPARATOR FOR EQUALITY AND INEQUALITY
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

// -------------------------
// Comparador de 6 bits para igualdade e desigualdade
// -------------------------
module comparator (output equal,        
                   output not_equal,    
                   input [5:0] a,       
                   input [5:0] b,       
                   input select);       

  wire [5:0] eq_bits;  

  // Verificação de igualdade usando XNOR bit a bit
  xnor (eq_bits[0], a[0], b[0]);
  xnor (eq_bits[1], a[1], b[1]);
  xnor (eq_bits[2], a[2], b[2]);
  xnor (eq_bits[3], a[3], b[3]);
  xnor (eq_bits[4], a[4], b[4]);
  xnor (eq_bits[5], a[5], b[5]);

  // bit equals
  and (equal, eq_bits[0], eq_bits[1], eq_bits[2], eq_bits[3], eq_bits[4], eq_bits[5]);

  // different bit
  nor (not_equal, equal);  // Se não for igual, é diferente

  // control key
  assign result = select ? not_equal : equal;
endmodule // comparator

// -------------------------
// Testbench
// -------------------------
module test_comparator;
  reg [5:0] x;
  reg [5:0] y;
  reg select;
  wire result;
  wire equal;
  wire not_equal;

  // comparator
  comparator comp_instance (.equal(equal), .not_equal(not_equal), .a(x), .b(y), .select(select));

  initial begin
    $display("Guia_0807 - Guilherme Soares Silva - 863485");
    $display("Test LU’s comparator for equality and inequality");
    
    // Testes
    x = 6'b101001; y = 6'b101001; select = 0;  
    #1 $display("x = %b, y = %b, select = %b, result = %b", x, y, select, result);
    
    x = 6'b111111; y = 6'b000001; select = 1;  
    #1 $display("x = %b, y = %b, select = %b, result = %b", x, y, select, result);

    x = 6'b010101; y = 6'b101010; select = 1;  
    #1 $display("x = %b, y = %b, select = %b, result = %b", x, y, select, result);
  end
endmodule // test_comparator

