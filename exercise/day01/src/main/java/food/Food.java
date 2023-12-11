package food;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Supplier;

public record Food(LocalDate expirationDate,
                   Boolean approvedForConsumption,
                   UUID inspectorId) {
    public boolean isEdible(Supplier<LocalDate> now) {
        return isExpirationDateAfter(now) &&
                isApprovedForConsumption() &&
                hasBeenInspectedFromValidInspector();
    }

    private boolean hasBeenInspectedFromValidInspector() {
        return this.inspectorId != null;
    }

    private Boolean isApprovedForConsumption() {
        return this.approvedForConsumption;
    }

    private boolean isExpirationDateAfter(Supplier<LocalDate> now) {
        return this.expirationDate.isAfter(now.get());
    }
}