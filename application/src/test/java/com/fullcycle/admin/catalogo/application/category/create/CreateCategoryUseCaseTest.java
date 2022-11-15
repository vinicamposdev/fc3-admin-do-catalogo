package com.fullcycle.admin.catalogo.application.category.create;

import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CreateCategoryUseCaseTest {
    /* Happy Path:
     * - Successfully create a category
     */
    @Test
    public void givenAValidCommand_whenCallsCreateCategoryUseCase_thenShouldReturnCategoryId() {
        final var expectedName = "Films";
        final var expectedDescription = "Most Watched";
        final var expectedIsActive = true;
        final CategoryGateway categoryGateway = Mockito.mock(CategoryGateway.class);
        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);
        final var useCase = new DefaultCreateCategoryUseCase(categoryGateway);
        when(categoryGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());
        Mockito.verify(categoryGateway, times(1)).create(argThat(aCategory ->
                Objects.equals(expectedName, aCategory.getName())
                        && Objects.equals(expectedDescription, aCategory.getDescription())
                        && Objects.equals(expectedIsActive, aCategory.isActive())
                        && Objects.nonNull(aCategory.getId())
                        && Objects.nonNull(aCategory.getCreatedAt())
                        && Objects.nonNull(aCategory.getUpdatedAt())
                        && Objects.isNull(aCategory.getDeletedAt())
        ));
    }

    /* Unhappy path
     * - Invalid Category
     * - Invalid Prop
     */
    /* Edge Case
     * - Generic Error from Gateway
     */
}
