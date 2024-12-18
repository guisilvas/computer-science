// -------------------------
// Guia_0903
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

module clock(clk);
	output clk;
	reg clk;
	
	initial begin
		clk = 1'b0;
	end
	
	always begin
		#12 clk = ~clk;
	end
endmodule // end clock

module pulse(signal, clock);
	input clock;
	output signal;
	reg signal;

	always @ (clock) begin
		signal = 1'b0;
		#36 signal = 1'b1;
		#36 signal = 1'b0;
	end
endmodule // end pulse

module Guia_0903;
	
	wire clock;
	clock clk(clock);

	wire p;
	pulse pls (p, clock);

	initial begin
		$dumpfile("Guia_0903.vcd");
		$dumpvars(1, clock, p);
		#480 $finish;
	end
endmodule
