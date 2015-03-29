#include <stdio.h>

main() {

	int i=0;
	int x=0;
	int days[] = {31,28,31,30,31,30,31,31,30,31,30,31};
	char *months[] = {"January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December"};
	
	for (i = 0; i < 12; i++) {
		for (x = 1; x <= days[i]; x++) {
			printf("%s %d\n", months[i], x);
		}
	}

return 0;

}
	
