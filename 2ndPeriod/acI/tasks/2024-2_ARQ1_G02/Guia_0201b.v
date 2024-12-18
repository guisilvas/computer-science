Tarefa: 2024-2_ARQ1_G01
Aluno: Guilherme Soares Silva
Matrícula: 863485

module Guia_0201_b;
// define data
real
x = 0 ; // decimal
real power2 = 1.0; // power of 2
integer
y = 7 ; // counter
reg [7:0] b = 8'b00010010; 
// actions
initial
begin : main
    $display ("Guia_0201_b - Conversion Result");
    $display ("b = 0.%8b", b);
    while (y >= 0)
    begin
        power2 = power2 / 2.0;
        if (b[y] == 1)
        begin
            x = x + power2;
        end
        $display ("x = %f", x);
        y = y - 1;
    end // end while
end // main
endmodule // Guia_0201_b
